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
package org.macula.extension.pdf.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;
import org.macula.extension.pdf.PdfDocumentGenerator;
import org.macula.extension.pdf.ResourceLoader;

/**
 * <p>
 * <b>TestPdfGenerator</b> is Pdf generator test.
 * </p>
 *
 * @since 2015年7月20日
 * @author wei_luo
 * @version $Id: codetemplates.xml 3814 2012-11-21 08:46:30Z wilson $
 */
public class TestPdfGenerator extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void test(File outputFile) throws IOException {

		long start = System.currentTimeMillis();

		// 模板数据
		OverseaVo overseaVo = new OverseaVo();

		overseaVo.setPolicyNo("1234567890123456");
		overseaVo.setHolderName("丽丽张123丽丽张123");
		overseaVo.setInsuredName("丽丽张123丽丽张123丽丽张123丽丽张123");
		overseaVo.setBeneficiaryName("测试受益人姓名");
		overseaVo.setBranchName("北京");
		overseaVo.setCompanyName("科索沃公司");
		overseaVo.setDestination("英国,俄罗斯,冰岛,日内瓦,威尼斯小镇");
		overseaVo.setHolderAdress("北京市屋顶后街金融大街14号中国人寿广场xxx曾x101室");
		overseaVo.setHolderPostCode("123456");
		overseaVo.setInsuredBirthday("2013-11-10");
		overseaVo.setInsuredIDNo("123456789012345678");
		overseaVo.setInsuredName("爱新觉罗启星");
		overseaVo.setInsuredPassportNo("测试护照号码123456789");
		overseaVo.setInsuredPhone("13112345678");
		overseaVo.setInsuredPingyinName("aixinjuluoqixing");
		overseaVo.setInsuredSex("女");
		overseaVo.setIssueDate("2013-11-12");
		overseaVo.setPeriod("十一年");
		overseaVo.setPremium("1009.00");
		overseaVo.setRelation("子女");
		overseaVo.setRemarks("这是一张测试保单,仅为测试,学习所用,请勿转载");
		overseaVo.setAccidentalSumInsured("150000");
		overseaVo.setEmergencySumInsured("500000");
		overseaVo.setMedicalSumInsured("220000");
		overseaVo.setImagePath(ResourceLoader.getPath("views/config/images"));

		// classpath 中模板路径
		String template = "config/templates/overseaAssistance.html";
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		pdfGenerator.generate(template, overseaVo, new FileOutputStream(outputFile));
		System.err.println(" \n pdf生成成功  IS OK path=\n" + outputFile);
		System.err.println("耗时time=" + (System.currentTimeMillis() - start) / 1000);

	}

	@Test
	public void testGenerate() throws IOException {
		File file = new File("d:\\temp\\" + System.currentTimeMillis() + ".pdf");
		if (!file.exists()) {
			file.createNewFile();
		}
		try {
			test(file);
		} catch (Exception ex) {
			System.err.println(" \n pdf生成失败");
			ex.printStackTrace();
		}

		assertNotNull("生成pdf文件为空", file);
		assertTrue("pdf文件不存在", file.exists());
		assertTrue("pdf生成文件大小错误", file.getFreeSpace() > 178000);

	}
}