package org.macula.extension.finder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.macula.base.security.util.SecurityUtils;
import org.macula.core.controller.BaseController;
import org.macula.core.mvc.annotation.FormBean;
import org.macula.core.mvc.annotation.OpenApi;
import org.macula.core.utils.HttpRequestUtils;
import org.macula.plugins.mda.finder.domain.FinderParam;
import org.macula.plugins.mda.finder.domain.FinderSchema;
import org.macula.plugins.mda.finder.domain.FinderStaticParam;
import org.macula.plugins.mda.finder.service.FinderSchemaService;
import org.macula.plugins.mda.finder.vo.FinderArgument;
import org.macula.plugins.mda.finder.vo.FinderDataModel;
import org.macula.plugins.mda.finder.vo.FinderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;


@Controller
public class FinderDialogController extends BaseController {

	@Autowired
	private FinderSchemaService schemaService;

	private boolean securityProtected = false;

	@RequestMapping(value = "/**/macula-mda/dialog/{finderCode}", method = { RequestMethod.GET, RequestMethod.POST })
	public View dialog(@PathVariable("finderCode") String finderCode, @RequestParam("tab") String tabViewCode,
			@FormBean(value = "staticParams") List<FinderStaticParam> staticParams, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		FinderSchema schema = schemaService.get(finderCode);

		String requestUrl = request.getRequestURI();
		String belongUrl = request.getContextPath() + "/" + schema.getRelativePath() + "macula-mda/dialog/"
				+ finderCode;
		if (securityProtected && !requestUrl.equals(belongUrl)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		return new FinderDialogView(finderCode, tabViewCode, staticParams);
	}

	@RequestMapping(value = "/**/macula-mda/dialog/{finderCode}/{tabViewCode}", method = RequestMethod.POST)
	@OpenApi
	public Page<?> page(@PathVariable("finderCode") String finderCode, @PathVariable("tabViewCode") String tabViewCode,
			@FormBean("filters") List<FinderArgument> filters,
			@FormBean("staticParams") List<FinderStaticParam> staticParams, Pageable pageable,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		FinderSchema schema = schemaService.get(finderCode);

		String requestUrl = request.getRequestURI();
		String belongUrl = request.getContextPath() + "/" + schema.getRelativePath() + "macula-mda/dialog/"
				+ finderCode + "/" + tabViewCode;
		if (securityProtected && !requestUrl.equals(belongUrl)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}

		FinderViewModel viewModel = new FinderViewModel(schema, tabViewCode, SecurityUtils.getUserContext(),
				staticParams);
		List<FinderArgument> arguments = new ArrayList<FinderArgument>();
		if (filters != null && !filters.isEmpty()) {
			for (FinderArgument finderArgument : filters) {
				FinderParam param = viewModel.getFinderNamedParam(finderArgument.getName());
				if (param != null) {
					finderArgument.updateValues(param, SecurityUtils.getUserContext());
					arguments.add(finderArgument);
				}
			}
		}
		return new FinderDataModel(schema, tabViewCode, SecurityUtils.getUserContext()).getData(arguments,
				staticParams, pageable, true);
	}

	@RequestMapping(value = "/**/macula-mda/dialog/{finder}/tabs", method = RequestMethod.GET)
	@OpenApi
	public Map<String, Long> tabViewTotals(@PathVariable("finder") String finderCode,
			@RequestParam("tab") String tabViewCode, @FormBean("staticParams") List<FinderStaticParam> staticParams,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		FinderSchema schema = schemaService.get(finderCode);

		String requestUrl = request.getRequestURI();
		String belongUrl = request.getContextPath() + "/" + schema.getRelativePath() + "macula-mda/dialog/"
				+ finderCode + "/tabs";
		if (securityProtected && !requestUrl.equals(belongUrl)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}

		FinderViewModel result = new FinderViewModel(schema, tabViewCode, SecurityUtils.getUserContext(), staticParams);
		return result.getTotals();
	}

	@RequestMapping(value = "/**/macula-mda/dialog/{finder}/filter", method = RequestMethod.GET)
	public String filterPage(@PathVariable("finder") String finderCode, @RequestParam("tab") String tabViewCode,
			Model model, @FormBean("staticParams") List<FinderStaticParam> staticParams, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		FinderSchema schema = schemaService.get(finderCode);

		String requestUrl = request.getRequestURI();
		String belongUrl = request.getContextPath() + "/" + schema.getRelativePath() + "macula-mda/dialog/"
				+ finderCode + "/filter";
		if (securityProtected && !requestUrl.equals(belongUrl)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}

		FinderViewModel result = new FinderViewModel(schema, tabViewCode, SecurityUtils.getUserContext(), staticParams);
		model.addAttribute("viewModel", result);

		return "org/macula/extendsion/finder/finder-filter";
	}

	@RequestMapping(value = "/**/macula-mda/dialog/{finder}/{detail}", method = RequestMethod.GET)
	public String detail(@PathVariable("finder") String finderCode, @PathVariable("detail") String detailCode,
			@RequestParam("tab") String tab, @RequestParam("item") String item, Model model,
			@FormBean("staticParams") List<FinderStaticParam> staticParams, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		FinderSchema schema = schemaService.get(finderCode);

		String requestUrl = request.getRequestURI();
		String belongUrl = request.getContextPath() + "/" + schema.getRelativePath() + "macula-mda/dialog/"
				+ finderCode + "/" + detailCode;
		if (securityProtected && !requestUrl.equals(belongUrl)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}

		FinderViewModel result = new FinderViewModel(schema, tab, SecurityUtils.getUserContext(), staticParams);

		model.addAttribute("viewModel", result);
		model.addAttribute("schema", schema);
		model.addAttribute("detail", schema.getDetailView(detailCode).getCode());
		model.addAttribute("item", item);
		if (HttpRequestUtils.isAjaxOrOpenAPIRequest(request)) {
			return "org/macula/extension/finder/finder-ajax-detail";
		}
		return "org/macula/extension/finder/finder-single-detail";
	}

	/**
	 * @param schemaService
	 *            the schemaService to set
	 */
	public void setSchemaService(FinderSchemaService schemaService) {
		this.schemaService = schemaService;
	}
}
