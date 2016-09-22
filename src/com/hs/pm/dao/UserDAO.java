package com.hs.pm.dao;

import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.hs.pm.bean.User;

public class UserDAO extends BaseDAO<User> {

	private static final int HASH_INTERATIONS = 1024;
	public static final String HASH_ALGORITHM = "SHA-1";
	private static final int SALT_SIZE = 8;
	
	
	/*
	 * �����û�
	 * 
	 */
	public boolean createUser(User u){
		entryptPassword(u);
		return super.create(u);
	}
	
	
	/*
	 * @param useraccount
	 * �û��ʺ�
	 * return User
	 * ���� User����
	 */
	public User searchUserByAccount(String useraccount){
		
		String hql = "from User u where u.useraccount like '" + useraccount + "'";
		return super.find(hql);		
	}

	/*
	 * @param username
	 * �û��ʺ�
	 * return User
	 * ���� User����
	 */
	public User searchUserByName(String username){
		
		String hql = "from User u where u.username like '" + username + "'";
		return super.find(hql);		
	}	
	
	/*
	 * 
	 * �����û�
	 * 
	 */
	
	public void updateUser(User u){
		entryptPassword(u);
		super.update(u);
	}
	
	/*
	 * @param User u
	 * ��������
	 * 
	 */
	private void entryptPassword(User u){
		String password = u.getPassword();
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		u.setSalt(Encodes.encodeHex(salt));
		
		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
		u.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	/*
	 * 
	 * �������벢У������������ȷ
	 */
	public boolean decryptPassword(User u, String inputPassword) {
		if(u == null || inputPassword == null){
			return false;
		}
		String dbSalt = u.getSalt();
		if(dbSalt == null || dbSalt.equals("")){
			entryptPassword(u);
			super.update(u);
		}
		String dbPassword = u.getPassword();
		byte[] deByte = Encodes.decodeHex(dbSalt);
		byte[] dePassword = Digests.sha1(inputPassword.getBytes(), deByte, HASH_INTERATIONS);
		String dePasswordStr = Encodes.encodeHex(dePassword);
		if (dePasswordStr.equals(dbPassword)) {
			return true;
		}
		return false;
	}
}
