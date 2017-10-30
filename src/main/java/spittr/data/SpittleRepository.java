package spittr.data;

import java.util.List;
import spittr.Spittle;

public interface SpittleRepository {
	/**
	 * 
	 * @param max
	 *            返回Spittle中，Spittle ID属性的最大值
	 * @param count
	 *            返回Spittle对象的个数
	 * @return
	 */
	List<Spittle> findSpittles(long max, int count);
}
