/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Filter;

import Entity.Bookmarks;
import Session.BookmarksFacadeLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author root
 */
public class setParam implements Filter {
    BookmarksFacadeLocal bookmarksFacade = lookupBookmarksFacadeLocal();

    private FilterConfig filterConfig = null;

    public setParam() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.setAttribute("check", "xxx");
//        List<Bookmarks> bookmarks = bookmarksFacade.findAll();
//        req.setAttribute("bookmarks", bookmarks);
        chain.doFilter(request, response);
    }

    /**
     * Return the filter configuration object for this filter.
     *
     * @return
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    private BookmarksFacadeLocal lookupBookmarksFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (BookmarksFacadeLocal) c.lookup("java:global/Core/Core-ejb/BookmarksFacade!Session.BookmarksFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
