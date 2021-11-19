package you.filedao;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;


public class FileToBlob implements FileDao{
	
	public static void main(String[] args)
	{
//		photoFormat("C:\\Users\\Tibame\\Desktop\\Github\\HTML\\img\\code1.jpg","C:\\Users\\Tibame\\Desktop\\Github\\HTML\\img\\code2.jpg",200,200);

	
	
	}
	
	public void sendBlob(InputStream is,PreparedStatement ps,String chooseFileType,int posIndex,String filePath)
	{
		byte[] bytes=new byte[1024];
		Boolean flag=false;
		if(is==null)
		{
			is =FileInOutDao.getInputStr(new File(filePath));
		}
			FileChannel fcl=((FileInputStream) is).getChannel();
			try {
//				將size及type取得的比較 && 判斷
				flag=(FileWorkDaoImpl.compareFileType(filePath, chooseFileType)&&FileWorkDaoImpl.compareFileSize(fcl.size(),chooseFileType));
				if(flag==false)
				{
					System.out.println("資料格式錯誤，重新選擇!");
					return;
				}
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		int longNumbob=0;
//		結果為false則資料不符合條件，不符合則返回。
		
		try {
			is=photoFormat(filePath,200,100);
//			while((longNumbob=is.read(bytes))!=-1) {
//				ps.setBlob(1, is);
//				while(is.read(bytes)!=-1) {
					ps.setBlob(posIndex,is);
//				}
//				ps.setBytes(posIndex, bytes);
//				Blob setBytes(long pos, byte[] bytes, int offset, int len) 
//				voidsetByte​(int parameterIndex, byte x) 将指定参数设置为给定的Java byte值。  
//				void setBytes​(int parameterIndex, byte[] x) 
//			}
		} catch ( SQLException  e) {
//		} catch ( SQLException | IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	sourceImgPath	帶切割圖片路徑
//	destImgPath		切割後目標圖片路徑
//	destImgW		所需圖片寬度
//	destImgH		所需圖片高度
	private static InputStream photoFormat(String filePath, int destImgW, int destImgH) {
//		原始圖片等比例縮小或放大後的圖片;
		int narrowImgW;
		int narrowImgH;
//		原圖片大小
		int sourceImgW;
		int sourceImgH;
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageOutputStream imops=null;
		InputStream is=null;
		try {
			is = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			imops = ImageIO.createImageOutputStream(baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		try {
			BufferedImage bufImg=ImageIO.read(is);
			
			sourceImgW=bufImg.getWidth();
			sourceImgH=bufImg.getHeight();
			
//			比較轉換的尺寸與原始圖片的踏小比例，若原始圖片較小，轉換就以高為準。
			if((float)sourceImgW/sourceImgH > (float)destImgW/destImgH)
			{
				narrowImgW=(int)(((float)destImgH/(float)sourceImgH)*sourceImgW);
				narrowImgH=destImgH;
				int cutNarrowImgSize=(narrowImgW-destImgW)/2;
//				取得縮小比例後的寬高，建立一個空白圖片空間
				BufferedImage narrowImg=new BufferedImage(narrowImgW,narrowImgH,BufferedImage.TYPE_INT_RGB);
				narrowImg.getGraphics().drawImage(bufImg.getScaledInstance(narrowImgW,narrowImgH,Image.SCALE_SMOOTH),0,0,null);
				
//				等比例縮放完成後，寬度與目標尺寸寬度比較，將多餘的寬部分分為兩部分，左邊刪除一部分
				Image image=narrowImg.getScaledInstance(narrowImgW,narrowImgH,Image.SCALE_DEFAULT);
				CropImageFilter cropFilter=new CropImageFilter(cutNarrowImgSize,0,narrowImgW-cutNarrowImgSize,narrowImgH);
				Image img=Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(),cropFilter));
				BufferedImage cutLiftNarrowImg=new BufferedImage(narrowImgW-cutNarrowImgSize,narrowImgH,BufferedImage.TYPE_INT_RGB);
				cutLiftNarrowImg.getGraphics().drawImage(img,0,0,null);
//				右邊刪除一部分
				image=cutLiftNarrowImg.getScaledInstance(narrowImgW-cutNarrowImgSize,narrowImgH,Image.SCALE_DEFAULT);
//				使用一個圖片過濾物件，限定一個固定長寬，用來裁切來源圖片。
				cropFilter=new CropImageFilter(0,0,narrowImgW-cutNarrowImgSize*2,narrowImgH);
				img=Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(),cropFilter));
				BufferedImage cutRightNarrowImg=new BufferedImage(narrowImgW-cutNarrowImgSize*2,narrowImgH,BufferedImage.TYPE_INT_RGB);
				Graphics grap = cutRightNarrowImg.getGraphics();
				grap.drawImage(img,0,0,null);
				
				ImageIO.write(cutRightNarrowImg,"png",imops);
				is=new ByteArrayInputStream(baos.toByteArray());
//				System.out.println("is=new ByteArrayInputStream(baos.toByteArray());"+(is==null));
				return is;
				
			}
			else
			{
				narrowImgW=destImgW;
				narrowImgH=(int)(((float)destImgW/(float)sourceImgW)*sourceImgH);
//				cutNarrowImgSize為需裁減掉的高度
				int cutNarrowImgSize=(narrowImgH-destImgH)/2;
				
				BufferedImage narrowImg=new BufferedImage(narrowImgW,narrowImgH,BufferedImage.TYPE_INT_RGB);
				narrowImg.getGraphics().drawImage(bufImg.getScaledInstance(narrowImgW,narrowImgH,Image.SCALE_SMOOTH),0,0,null);
				Image image=narrowImg.getScaledInstance(narrowImgW,narrowImgH,Image.SCALE_DEFAULT);
				CropImageFilter cropFilter=new CropImageFilter(0,cutNarrowImgSize,narrowImgW,narrowImgH-cutNarrowImgSize);
				Image img=Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(),cropFilter));
				BufferedImage cutTopNarrowImg=new BufferedImage(narrowImgW,narrowImgH-cutNarrowImgSize,BufferedImage.TYPE_INT_RGB);
				cutTopNarrowImg.getGraphics().drawImage(img,0,0,null);
				
				ImageIO.write(cutTopNarrowImg,"jpg",imops);
				is=new ByteArrayInputStream(baos.toByteArray());
//				System.out.println("is=new ByteArrayInputStream(baos.toByteArray());"+(is==null));
			}
			return is;
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		return is;
		
	}
}

