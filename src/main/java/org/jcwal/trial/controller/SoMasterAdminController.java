 
package org.jcwal.trial.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.jcwal.trial.domain.SoMaster;
import org.jcwal.trial.service.SoMasterService;
import org.macula.base.security.util.SecurityUtils;
import org.macula.core.exception.FormBindException;
import org.macula.core.mvc.annotation.FormBean;
import org.macula.core.mvc.annotation.OpenApi;
import org.macula.core.mvc.view.ExcelView;
import org.macula.plugins.mda.finder.view.FinderView;
import org.macula.plugins.mda.finder.vo.FinderSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

 
@Controller
public class SoMasterAdminController extends TrialAdminController {

	@Autowired
	private SoMasterService soMasterService;

	@RequestMapping("/so_master/index")
	public View index() {
		return new FinderView("SO_MASTER_DATASET");
	}

	/**
	 * 显示新增页面，自动匹配/admin/macula-trial/crud/form.ftl
	 */
	@RequestMapping(value = "/so_master/new", method = RequestMethod.GET)
	public String _new(Model model) {
		model.addAttribute("soMaster", toJson(new SoMaster()));
		return super.getRelativePath("/so_master/form");
	}

	/**
	 * 显示编辑页面，自动匹配/admin/macula-trial/crud/form.ftl
	 */
	@RequestMapping(value = "/so_master/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("id", id);
		SoMaster master = soMasterService.findOne(id);
		model.addAttribute("soMaster", toJson(master));
		return super.getRelativePath("/so_master/form");
	}

	/**
	 * 显示编辑页面，自动匹配/admin/macula-trial/crud/form.ftl
	 */
	@RequestMapping(value = "/so_master/edit", method = RequestMethod.POST)
	public String edit2(@FormBean("selection") FinderSelection selection, Model model) {
		Long id = Long.parseLong(selection.getItems().get(0));
		model.addAttribute("id", id);

		/////////////////////////////////////演示页面和数据一起返回///////////////////
		SoMaster master = soMasterService.findOne(id);
		model.addAttribute("soMaster", toJson(master));
		////////////////////////////////////////////////////////////////////////////

		return super.getRelativePath("/so_master/form");
	}

	/**
	 * 显示编辑页面，自动匹配/admin/macula-trial/crud/form.ftl
	 */
	@RequestMapping(value = "/so_master/edit2/{id}", method = RequestMethod.GET)
	public String edit3(@PathVariable Long id, Model model) {
		model.addAttribute("id", id);
		return super.getRelativePath("/so_master/form2");
	}

	/**
	 * 显示编辑页面，自动匹配/admin/macula-trial/crud/form.ftl
	 */
	@RequestMapping(value = "/so_master/edit2", method = RequestMethod.POST)
	public String edit4(@FormBean("selection") FinderSelection selection, Model model) {
		Long id = Long.parseLong(selection.getItems().get(0));
		model.addAttribute("id", id);
		return super.getRelativePath("/so_master/form2");
	}
	
	/**
	 * 读取数据，需指明ID
	 */
	@RequestMapping(value = "/so_master/read/{id}", method = RequestMethod.GET)
	@OpenApi
	public SoMaster read(@PathVariable Long id) {
		return soMasterService.findOne(id);
	}

	/**
	 * 保存数据
	 */
	@RequestMapping(value = "/so_master/save", method = RequestMethod.POST)
	@OpenApi
	public Long createOrUpdate(@FormBean("soMaster") @Valid SoMaster soMaster) {
		if (hasErrors()) {
			throw new FormBindException(getMergedBindingResults());
		}
		if (soMaster != null) {
			soMasterService.save(soMaster);
			return soMaster.getId();
		}
		return null;
	}

	/**
	 * 删除数据
	 */
	@RequestMapping(value = "/so_master/delete", method = RequestMethod.POST)
	@OpenApi
	public boolean delete(@FormBean("selection") FinderSelection selection, Model model) {
		for (String itemId : selection.getIterable(SecurityUtils.getUserContext(), true)) {
			if (itemId != null) {
				soMasterService.delete(soMasterService.findOne(Long.parseLong(itemId)));
			}
		}
		return true;
	}

	/**
	 * Excel生成演示
	 */
	@RequestMapping(value = "/so_master/excel.xls")
	public ModelAndView excel(@FormBean("selection") FinderSelection selection) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		list.add("word1");
		list.add("word2");
		model.put("wordList", list);
		model.put("name", "变量");
		return new ModelAndView(new ExcelView(getRelativePath("/so_master/excel")), model);
	}

	/**
	 * Excel生成演示
	 */
	@RequestMapping(value = "/so_master/excel2.xls")
	public ModelAndView excel2(@FormBean("selection") FinderSelection selection) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", "变量");
		return new ModelAndView(new ExcelView(getRelativePath("/so_master/excel")), model);
	}
}
