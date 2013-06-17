<#-- $Id: viewlog.ftl 3512 2012-08-23 01:12:33Z wilson $ -->
<#assign code="accesslog" />
<div class="tableform">	
	<div class="division" style="width:720px">
		<table cellspacing="0" cellpadding="0" border="0">
			<tbody>
				<tr>
					<th><label>住院号：</label></th>
					<td>
						${(patient.hospitalNumber)?default("&nbsp;")}
					</td>
					<th><label>病人姓名：</label></th>
					<td>
						${(patient.userName)?default("&nbsp;")}
					</td>
				</tr>										
			</tbody>
		</table>
		<h4>回访信息历史</h4>
		<#list visits as visit>
			<table cellspacing="0" cellpadding="0" border="1">
				<tbody>
					<tr>
						<th><label>计划回访日期：</label></th>
						<th style="text-align:left">
							${(visit.planVisitDate)?string(datePattern)}
						</th>
						<th><label>实际回访日期：</label></th>
						<th style="text-align:left">
							${(patient.actualVisitDate?string(datePattern))?default("&nbsp;")}
						</th>
					</tr>
					<tr>
						<th><label>回访医生：</label></th>
						<th style="text-align:left">
							${(visit.visitDoctor.nickname)?default("&nbsp;")}
						</th>
						<th><label>是否完成：</label></th>
						<th style="text-align:left">
							<#if visit.complete>是<#else>否</#if>
						</th>
					</tr>
					<tr>
						<th>回访内容：</th>
						<th colspan="3" style="text-align:left">
							<pre>${(visit.comment)?default("&nbsp;")}</pre>
						</th>
					</tr>
				</tbody>
			</table>
		</#list>
	</div>
</div>
