package org.taoningyu.javacv;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;

import java.nio.IntBuffer;
import java.util.Random;



public class Tuyacaise {
	 @SuppressWarnings("deprecation")
	public static void smooth(String filename,int grayVal) { 
	        IplImage image = cvLoadImage(filename);
	        double r, g, b,a;
	        r = g = b = a = 0.0;       

	        CvMat mtx = CvMat.createHeader(image.height(), image.width(), CV_32FC1);  
	        IntBuffer tmpIntBf = null;
	        cvGetMat(image, mtx, tmpIntBf, 0); 
	        
          
	        System.out.println(mtx.rows() + "x" + mtx.cols());
            Random random = new Random();
            int blue = 0;
            int green = 0;
            int red = 0;
	        for (int i = 0; i < mtx.rows(); i++)
	        {
	        	if(i%10==0){
	            	 
	   	        	 blue = random.nextInt()%255;
	   	        	 green = random.nextInt()%255;
	   	        	 red = random.nextInt()%255;
	   	        	
	            	}
	            for (int j = 0; j < mtx.cols(); j++)
	            {
	            
	                CvScalar rgb = cvGet2D(mtx, i, j);
	                r = rgb.val(0);
	                g = rgb.val(2);
	                b = rgb.val(1);
                    a = rgb.val(3);
	                double gray = (r + g + b) / 3;

	                CvScalar scalar = new CvScalar();
	               if(gray>grayVal){
	                	scalar.setVal(0, 255);
	 	                scalar.setVal(1, 255);
	 	                scalar.setVal(2, 255);
	               }else{
	            	    
	            	    scalar.setVal(0, blue);
	 	                scalar.setVal(1, green);
	 	                scalar.setVal(2, red);
	               }
	                cvSet2D(mtx, i, j, scalar);
	            }
	        }        

	       // i
	        if (image != null) {
	            cvSmooth(image, image);
	            cvSaveImage(filename.replace(".jpg", "")+"_5.jpg", image);
	            cvReleaseImage(image);
	        }
	    }

	public static void main(String[] args) {

       smooth(args[0],110);
       //smooth("E:/BaiduYunDownload/xwz2.jpg",110);
       //smooth("E:/BaiduYunDownload/xwz4.jpg",120);
	}

}
