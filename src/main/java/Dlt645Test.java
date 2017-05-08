import javax.comm.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

/**
 * Created by Administrator on 2017/4/27 0027.
 */
public class Dlt645Test implements SerialPortEventListener{
    private ArrayList<String> portList;
    private CommPortIdentifier portId;
    private SerialPort serialPort;
    private OutputStream outputStream;
    private InputStream inputStream;
    private int packetlength;

    static final char[] HEX_CHAR_TABLE = {
            '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    };

    public void scanPorts(){
        portList = new ArrayList<String>();
        Enumeration<?> en = CommPortIdentifier.getPortIdentifiers();
        CommPortIdentifier portId;
        while(en.hasMoreElements()){
            portId = (CommPortIdentifier)en.nextElement();
            if(portId.getPortType() == CommPortIdentifier.PORT_SERIAL){
                String name = portId.getName();
                if(!portList.contains(name)){
                    portList.add(name);
                }
            }
        }
        if(null == portList || portList.isEmpty()){
            System.out.println("未找到可用的串行端口号，程序无法启动！");
        }
    }

    public void openSerialPort(String portname){
        try{
            portId = CommPortIdentifier.getPortIdentifier(portname);
        }catch (NoSuchPortException e){
            System.out.println("抱歉，没有找到" + portname + "串行端口号！");
            return;
        }
    }

    public void setSerialPortParam(int rate,int data,int stop,int parity){
        try{
            serialPort = (SerialPort)portId.open("test",2000);
        }catch (PortInUseException e){
            System.out.println(serialPort.getName() +"端口已被占用");
            return;
        }

        try {
            serialPort.setSerialPortParams(rate,data,stop,parity);
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        }
        try{
            outputStream = serialPort.getOutputStream();
            inputStream = serialPort.getInputStream();
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            serialPort.addEventListener(this);
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }

        serialPort.notifyOnDataAvailable(true);
    }

    public void sendDataToSerialPort(byte[] b){
        try {
            outputStream.write(b);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serialEvent(SerialPortEvent e){
        switch (e.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                System.out.println("数据长度:" + packetlength);
                String data = toHexString(getPack(packetlength));
                System.out.println("返回数据:" + data);
        }
    }

    public Byte[] getPack(){
            List<Byte> msgPack = new ArrayList<Byte>();
            while(true){
                int newData = 0;
                try{
                    if((newData = inputStream.read()) != -1){
                        msgPack.add((byte) newData);
                    }else{
                        break;
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            return (Byte[])msgPack.toArray();
    }

    public byte[] getPack(int packetLength){
        while (true){
            byte[] msgPack = new byte[packetLength];
            for(int i=0;i<packetLength;i++){
                int newData = 0;
                try{
                    if((newData = inputStream.read()) != -1){
                        System.out.println("数据:" + newData);
                        msgPack[i] = (byte) newData;
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            return msgPack;
        }
    }
    public String toHexString(byte[] data){
        if(data == null || data.length == 0)
            return null;
        byte[] hex = new byte[data.length * 2];
        int index = 0;
        for(byte b : data){
            int v = b & 0xFF;
            hex[index++] = (byte) HEX_CHAR_TABLE[v >>> 4];
            hex[index++] = (byte) HEX_CHAR_TABLE[v & 0xF];
        }
        return new String(hex);
    }

    public String toHexString(Byte[] data){
        byte[] resultBytes = new byte[data.length];
        for(int i =0 ;i<data.length;i++){
            resultBytes[i] = data[i];
        }
        return toHexString(resultBytes);
    }

    public byte[] hexStringToBytes(String data){
        if(data == null || "".equals(data))
            return null;
        data = data.toUpperCase();
        int length = data.length()/2;
        char[] dataChars = data.toCharArray();
        byte[] byteData = new byte[length];
        for (int i = 0;i<length;i++){
            int pos = i * 2;
            byteData[i] = (byte)(charToByte(dataChars[pos]) << 4 | charToByte(dataChars[pos + 1]));
        }
        return byteData;
    }

    public byte charToByte(char c){
        return (byte)"0123456789ABCDEF".indexOf(c);
    }

    public int getPacketlength() {
        return packetlength;
    }

    public void setPacketlength(int packetlength) {
        this.packetlength = packetlength;
    }

    public static void main(String[] args){
        Dlt645Test dlt645 = new Dlt645Test();
        dlt645.setPacketlength(19);
        dlt645.scanPorts();
        System.out.println("串口：" + dlt645.portList);
        dlt645.openSerialPort(dlt645.portList.get(0));
        dlt645.setSerialPortParam(2400,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_EVEN);
        dlt645.sendDataToSerialPort(dlt645.hexStringToBytes("FE68AAAAAAAAAAAA681300DF16"));
    }
}
