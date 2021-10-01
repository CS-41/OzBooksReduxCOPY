/**
 * @author Elizabeth Allen - eallen12
 * CIS175 - Fall 2021
 * Sep 30, 2021
 */
package controller;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.OzBooks;


public class OzBookHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OzBooksRedux");
	
	public void insertBook(OzBooks ob) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(ob);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public List<OzBooks> showAllBooks(){
		EntityManager em = emfactory.createEntityManager();
		List<OzBooks> allBooks = em.createQuery("SELECT i from OzBooks i").getResultList();
		return allBooks;
		
	}

	public void deleteBook(OzBooks toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<OzBooks> typedQuery = em.createQuery("select ob from OzBooks ob where ob.bookTitle = :selectedTitle and ob.publishDate = :selectedDate", OzBooks.class);
		
		typedQuery.setParameter("selectedTitle", toDelete.getBookTitle());
		typedQuery.setParameter("selectedDate", toDelete.getPublishDate());
		
		typedQuery.setMaxResults(1);
		
		OzBooks result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public OzBooks searchForBookById(int bookIdToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		OzBooks found = em.find(OzBooks.class, bookIdToEdit);
		em.close();
		return found;
	}

	public void updateBook(OzBooks toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<OzBooks> searchForBookByTitle(String bookTitle) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<OzBooks> typedQuery = em.createQuery("select ob from OzBooks ob where ob.bookTitle = :selectedTitle", OzBooks.class);
		
		typedQuery.setParameter("selectedTitle", bookTitle);
		
		List<OzBooks> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<OzBooks> searchForDateByDate(int pubDate) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<OzBooks> typedQuery = em.createQuery("select ob from OzBooks ob where ob.publishDate = :selectedDate", OzBooks.class);
		
		typedQuery.setParameter("selectedDate", pubDate);
		
		List<OzBooks> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
	
}
