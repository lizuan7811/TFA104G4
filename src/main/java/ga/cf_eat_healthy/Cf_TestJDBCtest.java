package ga.cf_eat_healthy;

public class Cf_TestJDBCtest {

	public static void main(String[] args) {
		Cf_TestDAO dao = new Cf_TestDAOimpl();
		
		Cf_TestVO test = new Cf_TestVO();
		test.setTestno(12);
		test.setTname("周杰倫");
		test.setPhone("09112223333");
		dao.insert(test);
		System.out.println("新增成功");
	}

}
