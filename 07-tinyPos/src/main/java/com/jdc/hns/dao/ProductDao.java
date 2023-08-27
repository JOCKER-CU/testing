package com.jdc.hns.dao;

import java.util.ArrayList;
import java.util.List;

import com.jdc.hns.dto.Category;
import com.jdc.hns.dto.Product;
import static com.jdc.hns.utilis.PosConnection.getConnection;

public class ProductDao implements ReferenceServices<Product> {

	@Override
	public Product findById(int id) {
		String sql = """
				select p.id,p.name,p.dt_price,p.ws_price,c.id,c.name
				from product p join category c on p.category_id = c.id
				where p.id = ?
				"""  ;
		try(var con = getConnection(); var ps = con.prepareStatement(sql)){
			
			ps.setInt(1, id);
			var rs = ps.executeQuery();
			
			while(rs.next()) {
				var p = new Product();
				var c = new Category();
				
				c.setId(rs.getInt("c.id"));
				c.setName(rs.getString("c.name"));
				
				p.setId(rs.getInt("p.id"));
				p.setName(rs.getString("p.name"));
				p.setDetailPrice(rs.getInt("p.dt_price"));
				p.setWholeSalePrice(rs.getInt("p.ws_price"));
				p.setCategory(c);
				
				return p;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> findByNameLike(String name) {
		
		List<Product> list = new ArrayList<Product>();
		String sql = """
				select p.id,p.name,p.dt_price,p.ws_price,c.id,c.name
				from product p join category c on p.category_id = c.id
				where p.name like  lower(?)
				"""  ;
		try(var con = getConnection(); var ps = con.prepareStatement(sql)){
			
			ps.setString(1, name.toLowerCase().concat("%"));
			var rs = ps.executeQuery();
			
			while(rs.next()) {
				var p = new Product();
				var c = new Category();
				
				c.setId(rs.getInt("c.id"));
				c.setName(rs.getString("c.name"));
				
				p.setId(rs.getInt("p.id"));
				p.setName(rs.getString("p.name"));
				p.setDetailPrice(rs.getInt("p.dt_price"));
				p.setWholeSalePrice(rs.getInt("p.ws_price"));
				p.setCategory(c);
				
				list.add(p);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Product> findBy(int pId,int dPrice,int wPrice,String pName,String cName){
		List<Object> temp = new ArrayList<Object>();
		String sql = """
				select p.id,p.name,p.dt_price,p.ws_price,c.id,c.name
				from product p join category c on p.category_id = c.id
				where 1 = 1
				""";
		StringBuffer sb = new StringBuffer(sql);
		
		if(pId > 0) {
			sb.append(" and p.id = ?");
			temp.add(pId);
		}
		
		if(dPrice > 0) {
			sb.append(" and p.dt_price <= ?");
			temp.add(dPrice);
		}
		
		if(wPrice > 0) {
			sb.append(" and p.ws_price <= ?");
			temp.add(wPrice);
		}
		
		if(null != cName && !cName.isEmpty()) {
			sb.append(" and c.name like ?");
			temp.add(cName.toLowerCase());
		}
		
		if(null != pName && !pName.isEmpty() ) {
			sb.append(" and p.name  like ?");
			temp.add(pName.toLowerCase());
		}
		return getProducts(sb,temp);
		//return list;
	}

	private List<Product> getProducts(StringBuffer sb, List<Object> temp) {
		
		List<Product> list = new ArrayList<Product>();
		
		try(var con = getConnection(); var ps = con.prepareStatement(sb.toString())){
			
			for(int x = 0 ; x < temp.size() ; x++) {
							
				if(temp.get(x)  instanceof String s) {
					ps.setObject(x+1, s.concat("%"));		
				}else {
					ps.setObject(x+1,temp.get(x));
				}
			}
			
			var rs = ps.executeQuery();
			
			while(rs.next()) {
				var p = new Product();
				var c = new Category();
				
				c.setId(rs.getInt("c.id"));
				c.setName(rs.getString("c.name"));
				
				p.setId(rs.getInt("p.id"));
				p.setName(rs.getString("p.name"));
				p.setDetailPrice(rs.getInt("p.dt_price"));
				p.setWholeSalePrice(rs.getInt("p.ws_price"));
				p.setCategory(c);
				
				list.add(p);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}