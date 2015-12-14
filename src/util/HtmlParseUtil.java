package util;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.nodes.*;
import org.htmlparser.Node;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.beans.StringBean;

public class HtmlParseUtil {

	public HtmlParseUtil() {
		
	}

        
        public void processMyNodes(Node node)
        {
            if (node instanceof TextNode)
            {
                // downcast to TextNode
                TextNode text = (TextNode)node;
                // do whatever processing you want with the text
                System.out.println ("===text===\n" + text.getText ());
            }
            if (node instanceof RemarkNode)
            {
                // downcast to RemarkNode
                RemarkNode remark = (RemarkNode)node;
                // do whatever processing you want with the comment
                
                System.out.println ("===remark===\n" + remark.getText ());
            }
            else if (node instanceof TagNode)
            {
                // downcast to TagNode
                TagNode tag = (TagNode)node;
                // do whatever processing you want with the tag itself
                System.out.println ("===tag===\n" + tag.getText ());
                // ...
                // process recursively (nodes within nodes) via getChildren()
                NodeList nl = tag.getChildren ();
                if (null != nl)
                    try
                {
                	//for (NodeIterator i = nl.elements (); i.hasMoreElements(); )
                    for (NodeIterator i = nl.elements(); i.hasMoreNodes(); )
                        processMyNodes (i.nextNode());
                }
                catch (ParserException pe)
                {
                    pe.printStackTrace ();
                }
            } 
        } 
        
    	
        public static void main (String[] args)
        {
        	HtmlParseUtil htmlParseUtil = new HtmlParseUtil();
        	
            try
            {
                //Parser parser = new Parser ("http://www.crh.noaa.gov/product.php?site=NWS&issuedby=GJT&product=AFD&format=TXT&version=1&glossary=0");
                Parser parser = new Parser ("/uufs/chpc.utah.edu/common/home/mace_grp/web/html/stormvex/forecast/nws/nws_20101214_123425.html");
                
                //NodeFilter filter = new TagNameFilter("div");
                NodeFilter filter = new StringFilter("pre");
                
                NodeList list = parser.parse(filter);
                //NodeList list = parser.parse(null);
                //NodeList list = parser.extractAllNodeshatMatch(filter);
                System.out.println (list.size());
                //System.out.println (list.toHtml());
                System.out.println("=========================================");
                
                int ii=0;
                System.out.println("=========================================" + ii);
                Node node = list.elementAt(ii);
                //System.out.println(node.getText());
                //System.out.println(node.toPlainTextString());
                
                for (int itr=0; itr<list.size(); itr++)
                {
                	System.out.println("=========================================" + itr);
                   node = list.elementAt(itr);
                       // this will print the div html <div id='two'> some text two </div>
                   //System.out.println(node.getText());
                   //System.out.println(node.toPlainTextString());
                   htmlParseUtil.processMyNodes(node);
                       //System.out.println(node.toPlainTextString());
                       // now I need to extract the text from this div tag
                       /*
                        Parser tagParser = new Parser();
                       tagParser.setInputHTML(list.toHtml());
                       StringBean sb = new StringBean ();
                       tagParser.visitAllNodesWith (sb);
                       System.out.println(sb.getStrings ()); // this will print the content "some text two"
                       */                 
                   }
                
                //TextNode text = (TextNode)node;
                //System.out.println ("===text===\n" + text.getText ());
                
                //NodeList sublist = node.getChildren();
                //System.out.println (sublist.toString());
                //System.out.println (sublist.size());
                
            }
            catch (ParserException pe)
            {
                pe.printStackTrace ();
            }
        }
    }