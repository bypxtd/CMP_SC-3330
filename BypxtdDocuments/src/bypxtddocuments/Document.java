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
// 4 private fields
public class Document{
    private String title;
    private String author;
    private String body;
    private int version;
    
    // Constructor one
    public Document(String title, String author)
    {
        this.title = title;
        this.author = author;
        version = 0;
    }
    
    // Constructor two
    public Document(String title, String author, String body)
    {        
        super();
        this.title = title;
        this.author = author;
        this.body = body;

        version = 1;
    }
    
    // Methods
    public void setTitle(String title)
    {
        this.title = title;
        version++;
    }
    
    public void setAuthor(String author)
    {
        this.author = author;
    }
    
    public void setBody(String body)
    {
        this.body = body;
        version++;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getAuthor()
    {
        return author;
    }
    
    public String getBody()
    {
        return body;
    }
    
    public int getVersion()
    {
        return version;
    }
}
