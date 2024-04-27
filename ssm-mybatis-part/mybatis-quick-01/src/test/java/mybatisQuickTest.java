import com.ls.mapper.EmployeeMapper;
import com.ls.pojo.Employee;
import com.ls.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class mybatisQuickTest {

    @Test
    public  void test() throws IOException {
        // 1. 根据mybatis.xml创建SqlSessionFactory 对象
        //以输入流的方式加载mybatis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        //根据配置文件创建SqlSessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 2 .根据SqlSessionFactory 对象开启一个会话
        SqlSession sqlSession = sessionFactory.openSession();

        //3. 根据接口的class对象获取接口类型的对象（动态代理）
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        //4.使用代理类，调用方法
        Employee employee = employeeMapper.findById(1);
        System.out.println("employee = " + employee);

        //5. 关闭Sqlsession
        sqlSession.commit(); //提交事务，查询不需要，其他操作需要
        sqlSession.close(); //关闭会话



    }

    @Test
    public  void test2() throws IOException {

        //1.获取配置文件并根据配置文件创建SqlSessionFactory 对象
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2. 通过工程 打开会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3. 调用sqlSession的方法对数据库操作
        // select delete update insert
        //都是两个参数，第一个参数字符串，第二个参数object
        // 第一个参数填写sql语句的位置 namespace.id,
        // 第二个参数写sql的参数,如果sql有多个参数还要封装比较麻烦（mybatis的优化）
        // 返回值是object类，需要强制转化

         Student student = sqlSession.selectOne("aa.bbb", 1);
        System.out.println("student = " + student);

        //缺点： 1.参数串一个字符串,表示sql语句的位置，容易写错
        //      2.只能传一个参数，如果有多个参数还要封装。
        //      3.返回值问题，返回的是Object类，还要强转。

        //事务
        sqlSession.commit();
        sqlSession.close();
    }


}
