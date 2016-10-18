package fakeecommerce.dao;

import fakeecommerce.database.resources.FileKeyGeneratorImpl;
import fakeecommerce.database.resources.KeyGenerator;
import fakeecommerce.domain.Order;
import fakeecommerce.domain.Product;
import fakespring.AnnotationFakeSpring;
import fakespring.FakeSpring;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class DaoTest {

	private OrderDao orderDao;
	private LogisticsDao logisticsDao;

	@Before
	public void init() {
		FakeSpring fakeSpring = new AnnotationFakeSpring();
		fakeSpring.assemble("fakeecommerce");

		orderDao = fakeSpring.getBean(OrderDao.class);
		logisticsDao = fakeSpring.getBean(LogisticsDao.class);

		assertThat(orderDao, notNullValue());
		assertThat(logisticsDao, notNullValue());
	}

	//@Test
	public void keyGeneratorTest() {
		KeyGenerator keyGenerator = new FileKeyGeneratorImpl(new File("/tmp/fakeproduct/etc"), "test");
		keyGenerator.load();
		for(int i = 0 ; i<100 ; i++ ) {
			assertThat(keyGenerator.nextIntId(), is(i));
		}

		keyGenerator.flush();
	}

	@Test
	public void basicTest() {

		int elemNum = 40;

		for(int i = 0 ; i<elemNum ; i++ ) {
			System.out.println("insert : " + i);
			orderDao.insertOrder(getOrder());
		}

		for(int i = 0 ; i<elemNum ; i++ ) {
			System.out.println("get : " + i);
			Order order = orderDao.getOrderById((long)i);
			validOrder(order);
		}

		logisticsDao.updateStatus(10, Order.DeliverStatus.END);
		Order order = orderDao.getOrderById(10);
		assertThat(order.getDeliverStatus(), is(Order.DeliverStatus.END));
	}

	public void validOrder(Order order) {
		long orderId = order.getOrderId();
		assertThat(order.getDeliverStatus(), is(Order.DeliverStatus.NONE));
		assertThat(order.getOrdererName(), is("ordererName" + orderId));
		assertThat(order.getproductId(), is("productId" + orderId));

		List<Integer> productIds = order.getProducts();
		for(int i = 0 ; i<productIds.size() ; i++ ) {
			int productId = productIds.get(i);
			assertThat(i, is(productId));
		}
	}

	public Order getOrder() {
		Order order = new Order();
		long orderId = orderDao.nextOrderId();
		order.setOrderId(orderId);
		order.setproductId("productId" + orderId);
		order.setDeliverStatus(Order.DeliverStatus.NONE);
		order.setOrdererName("ordererName" + orderId);

		for(int i = 0 ; i<5 ; i++ ) {
			Product product = new Product();
			product.setId(i);
			order.addProduct(i);
		}

		return order;
	}


}
