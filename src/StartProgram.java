/**
 * @author Elizabeth Allen - eallen12
 * CIS175 - Fall 2021
 * Sep 30, 2021
 */

import java.util.List;
import java.util.Scanner;
import controller.OzBookHelper;
import model.OzBooks;


public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static OzBookHelper obh = new OzBookHelper();

		private static void addBook() {
			// TODO Auto-generated method stub
			System.out.print("Enter a title: ");
			String title = in.nextLine();
			System.out.print("Enter publication year: ");
			int pubDate = in.nextInt();
			OzBooks toAdd = new OzBooks(title, pubDate);
			obh.insertBook(toAdd);
		}

		private static void deleteBook() {
			// TODO Auto-generated method stub
			System.out.print("Enter title to delete: ");
			String title = in.nextLine();
			System.out.print("Enter publication year to delete: ");
			int pubDate = in.nextInt();
			OzBooks toDelete = new OzBooks(title, pubDate);
			obh.deleteBook(toDelete);
		}

		private static void editBook() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Title");
			System.out.println("2 : Search by Publication Year");
			int searchBy = in.nextInt();
			in.nextLine();
			List<OzBooks> foundItems;
			if (searchBy == 1) {
				System.out.print("Enter the title: ");
				String bookTitle = in.nextLine();
				foundItems = obh.searchForBookByTitle(bookTitle);
			} else {
				System.out.print("Enter the publication year: ");
				int pubDate = in.nextInt();
				foundItems = obh.searchForDateByDate(pubDate);
			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (OzBooks l : foundItems) {
					System.out.println(l.getBookId() + " : " + l.toString());
				}
				System.out.print("Which BOOK ID to edit: ");
				int bookIdToEdit = in.nextInt();

				OzBooks toEdit = obh.searchForBookById(bookIdToEdit);
				System.out.println("Retrieved " + toEdit.getBookTitle() + ": " + toEdit.getPublishDate());
				System.out.println("1 : Update Title");
				System.out.println("2 : Update Publication Year");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Title: ");
					String newTitle = in.nextLine();
					toEdit.setBookTitle(newTitle);
				} else if (update == 2) {
					System.out.print("New Publication Year: ");
					int newDate = in.nextInt();
					toEdit.setPublishDate(newDate);
				}

				obh.updateBook(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Oz Book Library ---");
			while (goAgain) {
				System.out.println("*  Select option:");
				System.out.println("*  1 -- Add book");
				System.out.println("*  2 -- Edit book");
				System.out.println("*  3 -- Delete book");
				System.out.println("*  4 -- View book list");
				System.out.println("*  5 -- Exit the library");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addBook();
				} else if (selection == 2) {
					editBook();
				} else if (selection == 3) {
					deleteBook();
				} else if (selection == 4) {
					viewTheList();
				} else {
					obh.cleanUp();
					System.out.println("   Happy Reading!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			// TODO Auto-generated method stub
			List<OzBooks> allBooks = obh.showAllBooks();
			for(OzBooks singleBook: allBooks) {
				System.out.println(singleBook.returnBookDetails());
			}

		}

	}
