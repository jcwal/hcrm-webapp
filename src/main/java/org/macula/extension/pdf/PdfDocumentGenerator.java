package org.macula.extension.pdf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.NestableRuntimeException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.SAXException;

import com.itextpdf.text.DocumentException;

public class PdfDocumentGenerator {
	private final static Logger logger = Logger.getLogger(PdfDocumentGenerator.class);

	private final static HtmlGenerator htmlGenerator = new HtmlGenerator();

	public boolean generate(String template, Object context, OutputStream outputStream) {
		String htmlContent = htmlGenerator.generate(template, context);
		this.generate(htmlContent, outputStream);
		return true;
	}

	/**
	 * Output a pdf to the specified outputstream
	 * 
	 * @param htmlContent
	 *            the htmlstr
	 * @param out
	 *            the specified outputstream
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws DocumentException 
	 * @throws Exception
	 */
	public void generate(String htmlContent, OutputStream outputStream) {
		ITextRenderer iTextRenderer = ITextRendererObjectFactory.borrowRender();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(htmlContent.getBytes()));
			iTextRenderer.setDocument(doc, null);
			iTextRenderer.layout();
			iTextRenderer.createPDF(outputStream);
		} catch (Exception ex) {
			logger.error("Generate pdf error.", ex);
			throw new NestableRuntimeException(ex);
		} finally {
			if (outputStream != null) {
				IOUtils.closeQuietly(outputStream);
			}
			if (iTextRenderer != null) {
				ITextRendererObjectFactory.returnRender(iTextRenderer);
			}
		}
	}

}