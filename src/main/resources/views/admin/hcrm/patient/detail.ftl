<#-- $Id: viewlog.ftl 3512 2012-08-23 01:12:33Z wilson $ -->
<#assign code="accesslog" />
<div class="tableform">	
	<div class="division">
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
				<tr>
					<th><label>性别：</label></th>
					<td>
						${(patient.sex)?default("&nbsp;")}
					</td>
					<th><label>年龄：</label></th>
					<td>${(patient.age)?default("&nbsp;")}</td>
				</tr>
				<tr>
					<th><label>电话：</label></th>
					<td>
						${(patient.telephone)?default("&nbsp;")}
					</td>
					<th><label>手机：</label></th>
					<td>
						${(patient.moble)?default("&nbsp;")}
					</td>							
				</tr>
				<tr>
					<th><label>住址：</label></th>
					<td colspan="3">
						${(patient.address)?default("&nbsp;")}
					</td>							
				</tr>
				<tr>
					<th><label>入院时间：</label></th>
					<td>
						${(patient.admissionTime?string(datePattern))?default("&nbsp;")}
					</td>
					<th><label>病种：</label></th>
					<td>
						${(patient.disease)?default("&nbsp;")}
					</td>							
				</tr>
				<tr>
					<th><label>出院时间：</label></th>
					<td>
						${(patient.dischargeTime?string(datePattern))?default("&nbsp;")}
					</td>
					<th><label>出院情况：</label></th>
					<td>
						${(patient.dischargeType)?default("&nbsp;")}
					</td>							
				</tr>
				<tr>
					<th><label>管床医生：</label></th>
					<td>
						${(patient.bedDoctor.nickname)?default("&nbsp;")}
					</td>
					<th><label>科室：</label></th>
					<td>
						${(patient.department)?default("&nbsp;")}
					</td>							
				</tr>
				<tr>
					<th><label>主治医生：</label></th>
					<td>
						${(patient.attendingDoctor.nickname)?default("&nbsp;")}
					</td>
					<th><label>住院医生：</label></th>
					<td>
						${(patient.residentDoctor.nickname)?default("&nbsp;")}
					</td>							
				</tr>
				<tr>
					<th><label>出院诊断：</label></th>
					<td colspan="3">
						${(patient.dischargeDiagnosis?replace('\n','<br/>'))?default("&nbsp;")}
					</td>
				</tr>
				<tr>
					<th><label>病理诊断：</label></th>
					<td colspan="3">
						${(patient.pathologicalDiagnosis?replace('\n','<br/>'))?default("&nbsp;")}
					</td>							
				</tr>
				<tr>
					<th><label>化验检查结果：</label></th>
					<td colspan="3">
						${(patient.treatment?replace('\n','<br/>'))?default("&nbsp;")}
					</td>
				</tr>
				<tr>
					<th><label>出院医嘱：</label></th>
					<td colspan="3">
						${(patient.dischargeAdvice?replace('\n','<br/>'))?default("&nbsp;")}
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
							${(visit.comment?replace('\n','<br/>'))?default("&nbsp;")}
						</th>
					</tr>
				</tbody>
			</table>
		</#list>
	</div>
</div>
