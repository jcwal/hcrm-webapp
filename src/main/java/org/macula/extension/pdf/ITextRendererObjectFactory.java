/**
 * Copyright 2010-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.macula.extension.pdf;

import java.io.File;
import java.io.IOException;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;

/**
 * <p>
 * <b>ITextRendererObjectFactory</b> is Pdf IText Factory.
 * </p>
 *
 * @since 2015年7月20日
 * @author wei_luo
 * @version $Id: codetemplates.xml 3814 2012-11-21 08:46:30Z wilson $
 */
public class ITextRendererObjectFactory extends BasePoolableObjectFactory {

	private static Logger logger = LoggerFactory.getLogger(ITextRendererObjectFactory.class);

	private static GenericObjectPool itextRendererObjectPool = null;

	public static ITextRenderer borrowRender() {
		ITextRenderer iTextRenderer = null;
		try {
			iTextRenderer = (ITextRenderer) ITextRendererObjectFactory.getObjectPool().borrowObject();
		} catch (Exception ex) {
			logger.error("Cannot borrow object from poll.", ex);
			if (iTextRenderer != null) {
				try {
					ITextRendererObjectFactory.getObjectPool().invalidateObject(iTextRenderer);
				} catch (Exception e) {
					// ignore
				}
				iTextRenderer = null;
			}
		}
		return iTextRenderer;
	}

	public static void returnRender(ITextRenderer iTextRenderer) {
		try {
			ITextRendererObjectFactory.getObjectPool().returnObject(iTextRenderer);
		} catch (Exception ex) {
			logger.error("Cannot return object from pool.", ex);
		}
	}

	@Override
	public Object makeObject() throws Exception {
		ITextRenderer renderer = createTextRenderer();
		return renderer;
	}

	/**
	* Get object pool, support 500 threads, iterator 10 times
	*/
	protected static GenericObjectPool getObjectPool() {
		synchronized (ITextRendererObjectFactory.class) {
			if (itextRendererObjectPool == null) {
				itextRendererObjectPool = new GenericObjectPool(new ITextRendererObjectFactory());
				GenericObjectPool.Config config = new GenericObjectPool.Config();
				config.lifo = false;
				config.maxActive = 15;
				config.maxIdle = 5;
				config.minIdle = 1;
				config.maxWait = 5 * 1000;
				itextRendererObjectPool.setConfig(config);
			}
		}

		return itextRendererObjectPool;
	}

	/**
	* initialize.
	*/
	protected static synchronized ITextRenderer createTextRenderer() throws DocumentException, IOException {
		ITextRenderer renderer = new ITextRenderer();
		ITextFontResolver fontResolver = renderer.getFontResolver();
		addFonts(fontResolver);
		return renderer;
	}

	/**
	* Add fonts.
	*/
	protected static ITextFontResolver addFonts(ITextFontResolver fontResolver) throws DocumentException, IOException {
		File fontsDir = new File(ResourceLoader.getPath("classpath:fonts"));
		if (fontsDir != null && fontsDir.isDirectory()) {
			File[] files = fontsDir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f == null || f.isDirectory()) {
					break;
				}
				fontResolver.addFont(f.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			}
		}
		return fontResolver;
	}
}