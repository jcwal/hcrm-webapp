package org.macula.extension.finder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.macula.base.security.util.SecurityUtils;
import org.macula.core.controller.BaseController;
import org.macula.core.mvc.annotation.FormBean;
import org.macula.core.mvc.view.ExcelView;
import org.macula.plugins.mda.finder.domain.FinderParam;
import org.macula.plugins.mda.finder.domain.FinderSchema;
import org.macula.plugins.mda.finder.domain.FinderStaticParam;
import org.macula.plugins.mda.finder.service.FinderSchemaService;
import org.macula.plugins.mda.finder.vo.FinderArgument;
import org.macula.plugins.mda.finder.vo.FinderDataModel;
import org.macula.plugins.mda.finder.vo.FinderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * <p>
 * <b>FinderReportExcelController</b> 是导出Excel Controller
 * </p>
 *
 */
@Controller
public class FinderReportExcelController extends BaseController {

	@Autowired
	private FinderSchemaService schemaService;

	@RequestMapping(value = "/**/macula-mda/finder/{finderCode}/{tabViewCode}/excel", method = RequestMethod.POST)
	public ExcelView excel(@PathVariable("finderCode") String finderCode,
			@PathVariable("tabViewCode") String tabViewCode, @FormBean("filters") List<FinderArgument> filters,
			@FormBean("staticParams") List<FinderStaticParam> staticParams, Pageable pageable,
			HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		FinderSchema schema = schemaService.get(finderCode);

		String requestUrl = request.getRequestURI();
		String belongUrl = request.getContextPath() + "/" + schema.getRelativePath() + "macula-mda/finder/"
				+ finderCode + "/" + tabViewCode + "/excel";
		if (!requestUrl.equals(belongUrl)) {
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
		List<?> content = new FinderDataModel(schema, tabViewCode, SecurityUtils.getUserContext()).getData(arguments,
				staticParams, new PageRequest(0, Integer.MAX_VALUE), true).getContent();

		//		List<String> columns
		//		viewModel.getColumns();

		model.addAttribute("viewModel", viewModel);
		model.addAttribute("queryResult", content);
		return new ExcelView("/org/macula/plugins/mda/finder/view/FinderResult");
	}
}
