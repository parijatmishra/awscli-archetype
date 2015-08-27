#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
    	ArgumentParser parser = ArgumentParsers.newArgumentParser("Main")
    			.defaultHelp(true)
    			.description("Sample CLI application");
    	parser.addArgument("--profile")
    			.help("AWS IAM profile to use")
    			.setDefault("default");
    	Namespace ns = null;
    	try {
    		ns = parser.parseArgs(args);
    	} catch (ArgumentParserException e) {
    		parser.handleError(e);
    		System.exit(1);
    	}
    	
    	String profileName = ns.getString("profile");
    	AWSCredentialsProvider credsProvider =
    			new ProfileCredentialsProvider(profileName);
    	
    	// List S3 buckets
    	AmazonS3 s3Client  = new AmazonS3Client(credsProvider);
    	for (Bucket bucket : s3Client.listBuckets()) {
    		System.out.println("Bucket: " + bucket.getName());
    		System.out.println("    Region: " + s3Client.getBucketLocation(bucket.getName()));
    	}
    }
}
