/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.domain.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.SessionFactory;

/**
 * Super class for builders that build domain objects. 
 * This super class is able to store the product before returning it
 * using an {@link EntityManager}.
 * 
 * @author Nazanin
 * 
 * 
 */
public abstract class EntityBuilder<T extends Serializable> {
       
       private SessionFactory sf;
	protected T product;

	{
		initProduct();
	}

	public T build(Boolean... doNotPersist) {
		T product = assembleProduct();
		if (ArrayUtils.isEmpty(doNotPersist)
				|| (ArrayUtils.isNotEmpty(doNotPersist) && doNotPersist[0] == Boolean.FALSE)) {
                   sf.getCurrentSession().persist(product);
                    //EntityBuilderManager.getEntityManager().persist(product);
		}
		T temp = product;
		initProduct();
		return temp;
	}

	abstract void initProduct();

	abstract T assembleProduct();

	public static class SessionFactoryBuilderManager {
		private static ThreadLocal<SessionFactory> entityManagerHolder = new ThreadLocal<SessionFactory>();

		public static void setSessionFactory(SessionFactory sf) {
			entityManagerHolder.set(sf);
		}

		public static void clearSessionFactory() {
			entityManagerHolder.remove();
		}

		public static SessionFactory getSessionFactory() {
			return entityManagerHolder.get();
		}
	}
}