package org.taoningyu.javacv;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;

import java.nio.IntBuffer;



public class Tuyaheibai {
	 @SuppressWarnings("deprecation")
	public static void smooth(String filename,int grayVal) { 
	        IplImage image = cvLoadImage(filename);
	        double r, g, b,a;
	        r = g = b = a = 0.0;       

	        CvMat mtx = CvMat.createHeader(image.height(), image.width(), CV_32FC1);  
	        IntBuffer tmpIntBf = null;
	        cvGetMat(image, mtx, tmpIntBf, 0); 
	        
          
	        System.out.println(mtx.rows() + "x" + mtx.cols());

	        for (int i = 0; i < mtx.rows(); i++)
	        {
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
	            	    scalar.setVal(0, 0);
	 	                scalar.setVal(1, 0);
	 	                scalar.setVal(2, 0);
	               }
	                cvSet2D(mtx, i, j, scalar);
	            }
	        }        

	       // i
	        if (image != null) {
	            cvSmooth(image, image);
	            cvSaveImage(filename.replace(".jpg", "")+"_3.jpg", image);
	            cvReleaseImage(image);
	        }
	    }

	public static void main(String[] args) {

       smooth(args[0],100);
       //smooth("E:/BaiduYunDownload/xwz2.jpg",110);
       //smooth("E:/BaiduYunDownload/xwz4.jpg",120);
	}

}
