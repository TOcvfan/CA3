/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

/**
 *
 * @author Silas
 */
public class Tester {
 
    private static UserFacade uf = new UserFacade();
  
    
    
    public static void main(String[] args) {
        
        
    
        System.out.println(uf.getUserByUserId("John").getRoles());
        
    }
    
    
}
