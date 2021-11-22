package you.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;

import basicutil.Util;
import oracle.net.aso.e;
import you.basicdao.BaseDaoImpl;
import you.contents.FinalStaticFile;


public class Test {
	public static void main(String[] args)
	{
		
		ArrayList<String[]> arr=new ArrayList<String[]>();
		String[] ss= {"1","2","3","4"};
		String[] ss1= {"5","6","7","8"};
		String[] ss2= {"1","2","3","4"};
		int i=0;
		arr.add(ss);
		arr.add(ss1);
		arr.add(ss2);
		i++;
		for(String[] ins:arr)
		{
			System.out.print(ins[0]+" ");
			System.out.print(ins[1]+" ");
			System.out.print(ins[2]+" ");
			System.out.print(ins[3]+" ");
			System.out.println();
		}
		Iterator<String[]> it=arr.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		
//		Connection conn=Util.getConnection();
//		PreparedStatement ps=null;
//		BaseDaoImpl bdi=new BaseDaoImpl();
//		bdi.updateData(conn, ps, FinalStaticFile.ADMIN_UPDATE);
//		bdi.deleteData(conn, ps, FinalStaticFile.ADMIN_DELETE,0);
//		bdi.selectData(conn, ps, FinalStaticFile.ADMIN_SELECT,"");
//		bdi.insertData(conn, ps, FinalStaticFile.ADMIN_INSERT);
		
		
	}
}