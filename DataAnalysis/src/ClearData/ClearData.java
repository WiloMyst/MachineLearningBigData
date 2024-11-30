package ClearData;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.log4j.BasicConfigurator;
/**
 * MapReduce 
 * */
public class ClearData {
    /**
     * Map
     * */
    public static class MyMapper extends Mapper<LongWritable, Text, Text, Text> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        	 //不处理第一行数据
            if (key.toString().equals("0")) {
                return;
            } else {
            }
        	 String line[] = value.toString().split(",");
             int date = Integer.valueOf(line[0]);
             String[] time = line[1].split("-");
             String times = time[0]+time[1]+time[2];
             int score = Integer.valueOf(line[2]);
             int sleep_time = Integer.valueOf(line[3]);
             String q_sleep = line[4];
             String s_sleep = line[5];
             String xl = line[6];
             String s_time = line[7];
             String s_time1 = line[8];
             float q_sleeps =  (float) (Float.valueOf(q_sleep.split("%")[0])*0.01);
             float s_sleeps =  (float) (Float.valueOf(s_sleep.split("%")[0])*0.01);
             float xls =  (float) (Float.valueOf(xl.split("%")[0])*0.01); 
             int s_times = Integer.valueOf(s_time.split(":")[0]); 
             int s_timess = Integer.valueOf(s_time1.split(":")[0]); 
             context.write(new Text(date+","+time+","+score+","+sleep_time+","+q_sleeps+","+s_sleeps+","+xls+","+s_times+","+s_timess), new Text(""));  
        }
    }
    /**
     * Reduce
     * */
    public  static class MyReducer extends Reducer<Text, Text, Text, Text> {
    	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for(Text value: values) {
            	  context.write(new Text(key.toString()),new Text(""));   
            }
        }
    }
    /**
     * Driver
     * */
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        BasicConfigurator.configure();
        Job job = Job.getInstance(conf);
        job.setJarByClass(ClearData.class);
        job.setMapOutputValueClass(Text.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        FileInputFormat.setInputPaths(job,new Path("hdfs://192.168.44.99:9000/datas.csv"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.44.99:9000/output/result1"));
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0:1);
    }
}