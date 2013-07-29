<#-- $Id: viewlog.ftl 3512 2012-08-23 01:12:33Z wilson $ -->
<#assign code="accesslog" />
<div class="tableform">	
	<div class="division">
		<h4>此出院时间段病人住院信息</h4>
		<table cellspacing="0" cellpadding="0" border="1" class="treeTable gridlist" style="width:70%">
			<thead>
				<tr>
					<th>住院号</th>
					<th>病人姓名</th>
					<th>入院时间</th>
					<th>出院时间</th>
					<th>住院天数</th>
					<th>住院医生</th>
					<th>病人电话</th>
					<th>病人手机</th>
				</tr>
			</thead>
			<tbody>
			<#if patients?exists && patients?has_content>
				<#list patients as patient>				
				<tr>
					<td>
						${(patient.hospitalNumber)?default("&nbsp;")}
					</td>
					<td>
						${(patient.userName)?default("&nbsp;")}
					</td>
					<td>
						${(patient.admissionTime?string(datePattern))?default("&nbsp;")}
					</td>
					<td>
						${(patient.dischargeTime?string(datePattern))?default("&nbsp;")}
					</td>
					<td>
						${(patient.residentDays)?default("&nbsp;")}
					</td>
					<td>
						${(patient.residentDoctor.nickname)?default("&nbsp;")}
					</td>
					<td>
						${(patient.telephone)?default("&nbsp;")}
					</td>
					<td>
						${(patient.moble)?default("&nbsp;")}
					</td>							
				</tr>
				</#list>
			<#else>
				<tr>
					<td colspan="8">未找到相关住院病人信息！</td>
				</tr>
			</#if>		 
			</tbody>
		</table>		
	</div>
</div>
