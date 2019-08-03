/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bypxtddocuments;

/**
 *
 * @author bruce
 */
public class BypxtdDocuments {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Create first instance of document
        Document instance_document1 = new Document("Another Life", "Sally Smith");
        // Assign first instance of document to document1 variable
        Document document1 = instance_document1;
        // set body of document1
        document1.setBody("The grass is alway greener on the other side.");
        
        // Create second instance of document
        Document instance_document2 = new Document("Final Word", "Karen Jones", "We should plan for the worst and hope for the best");
        // Assign secon instance of document to document2 variable
        Document document2 = instance_document2;
        // set title of document2
        document2.setTitle("Final Words");
        
        // Print using the get method
        // Document 1
        System.out.println("document1:");
        System.out.println("title: " + document1.getTitle());
        System.out.println("author " + document1.getAuthor());
        System.out.println("body: " + document1.getBody());
        System.out.println("version: " + document1.getVersion());
        
        // Document 2
        System.out.println("\n");
        System.out.println("document2:");
        System.out.println("title: " + document2.getTitle());
        System.out.println("author: " + document2.getAuthor());
        System.out.println("body: " + document2.getBody());
        System.out.println("version: " + document2.getVersion());
    }
    
}
