package com.x.utils.database;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 生成排序
 * 
 */
public class OrderQuery {

	/**
	 * 顺序
	 */
	private LinkedHashMap<String, Order> orders;

	public OrderQuery() {
	}

	/**
	 * @return the orders
	 */
	public final boolean isHasOrder() {
		return orders == null && orders.size() > 0;
	}

	/**
	 * 返回排序的sql字符串 <br>
	 * 
	 * @return String .例如："name desc,sex asc,description desc"
	 */
	public final String getOrderClause() {
		if (orders != null && orders.size() > 0) {
			StringBuffer buffer = new StringBuffer(" ORDER BY ");
			for (Map.Entry<String, Order> entry : orders.entrySet()) {
				Order order = entry.getValue();
				buffer.append(order.orderBy);
				if (StringUtils.isNoneBlank(order.order)) {
					buffer.append(' ');
					buffer.append(order.order);
				}
				buffer.append(',');
			}
			String sql = buffer.toString();
			if (sql.endsWith(",")) {
				sql = sql.substring(0, sql.length() - 1);
			}
			return sql;
		} else {
			return null;
		}
	}

	public void clearOrders() {
		if (orders != null) {
			this.orders.clear();
		}
	}

	public final boolean appendOrder(String columnName, DatabaseOrder order) {
		if (StringUtils.isBlank(columnName) || order == null) {
			return false;
		}
		if (orders == null) {
			orders = new LinkedHashMap<String, Order>();
		}
		if (orders.containsKey(columnName)) {
			((Order) orders.get(columnName)).order = order.name();
		} else {
			Order o = new Order();
			o.order = order.name();
			o.orderBy = columnName;
			orders.put(columnName, o);
		}
		return true;
	}

	public final boolean appendOrder(String columnName, String order) {
		if (StringUtils.isBlank(columnName) || order == null) {
			return false;
		}
		if (orders == null) {
			orders = new LinkedHashMap<String, Order>();
		}
		if (orders.containsKey(columnName)) {
			((Order) orders.get(columnName)).order = order;
		} else {
			Order o = new Order();
			o.order = order;
			o.orderBy = columnName;
			orders.put(columnName, o);
		}
		return true;
	}

	private class Order {
		private String orderBy;
		private String order;
	}

	public static void main(String[] args) {
		OrderQuery a = new OrderQuery();
		a.appendOrder("a", DatabaseOrder.asc);
		a.appendOrder("b", DatabaseOrder.desc);
		System.out.println(a.getOrderClause());
	}
}
