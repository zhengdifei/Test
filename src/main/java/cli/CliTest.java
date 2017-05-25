package cli;

import org.apache.commons.cli.*;

/**
 * Created by Administrator on 2017/5/24 0024.
 */
public class CliTest {
    public static void main(String[] args){
        CommandLineParser parser = new BasicParser();
        Options options = new Options();
        options.addOption("h","help",false,"Print this usage information");
        options.addOption("v","verbose",false,"Print out VERBOSE information");
        options.addOption("f","file",true,"File to save");

        try {
            CommandLine cl = parser.parse(options,args);
            boolean verbose = false;
            String file = "";

            if(cl.hasOption('h')){
                System.out.println("help message");
                System.exit(0);
            }

            if(cl.hasOption('v')){
                verbose = true;
                System.out.println(verbose);
            }

            if(cl.hasOption('f')){
                file = cl.getOptionValue('f');
                System.out.println(file);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
