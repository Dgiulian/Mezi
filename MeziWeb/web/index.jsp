<%@page import="utils.PathCfg"%>
<!DOCTYPE html>
<html lang="en">
<head>
	
    <%@include file="tpl_head.jsp" %>
		
</head>

<body>
    <%@include  file="tpl_header.jsp" %>
	
		<div class="container">
		<div class="row">
				
                    <%@include file="tpl_sidebar.jsp" %>
						
			<!-- start: Content -->
			<div id="content" class="col-lg-10 col-sm-11 ">
			
			
			<div class="row">
				
				<div class="col-sm-12 col-md-9">
					<ol class="breadcrumb">
					  	<li><a href="index.html#">Mezi</a></li>
					  	<li class="active" >Dashboard</li>

<!--						<div class="form-group choose-date hidden-xs">
						  	<div class="controls">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input type="text" class="form-control" id="daterange" value="09/09/2013 - 09/28/2013">
									<span class="input-group-addon"><i class="fa fa-chevron-down"></i></span>
								</div>
						  	</div>
						</div>-->

					</ol>
					
					<div class="row">

						<div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
							<div class="smallstat box">
								<div class="boxchart-overlay blue">
									<div class="boxchart">5,6,7,2,0,4,2,4,8,2,3,3,2</div>
								</div>	
								<span class="title">Clientes</span>
								<span class="value">4 589</span>
								<a href="" class="more">
									<span>View More</span>
									<i class="fa fa-chevron-right"></i>
								</a>	
							</div>
						</div><!--/col-->

						<div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
							<div class="smallstat box">
								<div class="linechart-overlay red">
									<div class="linechart">1,2,6,4,0,8,2,4,5,3,1,7,5</div>
								</div>	
								<span class="title">Deals</span>
								<span class="value">789</span>
								<a href="" class="more">
									<span>View More</span>
									<i class="fa fa-chevron-right"></i>
								</a>
							</div>
						</div><!--/col-->

						<div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
							<div class="smallstat box">
								<i class="fa fa-usd green"></i>
								<span class="title">Income</span>
								<span class="value">$1 99,99</span>
								<a href="" class="more">
									<span>View More</span>
									<i class="fa fa-chevron-right"></i>
								</a>
							</div>
						</div><!--/col-->

						<div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
							<div class="smallstat box">
								<div class="piechart-overlay blue">
									<div class="piechart" data-percent="55"><span>55</span>%</div>
								</div>	
								<span class="title">Account</span>
								<span class="value">$199,99</span>
								<a href="" class="more">
									<span>View More</span>
									<i class="fa fa-chevron-right"></i>
								</a>
							</div>
						</div><!--/col-->

					</div><!--/row-->

					<div class="row">
						
						<div class="col-xs-12">
						
							<div class="box">
								<div class="box-header">
									<h2><i class="fa fa-bar-chart-o"></i>Traffic</h2>
									<div class="box-icon">
										<a href="index.html#" class="btn-setting"><i class="fa fa-wrench"></i></a>
										<a href="index.html#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
									<ul class="nav nav-tabs" id="mainCharts">
									  	<li id="chart24h"><a href="index.html#24h">24h</a></li>
									  	<li id="chartWeek"><a href="index.html#week">week</a></li>
									  	<li id="chartMonth" class="active"><a href="index.html#month">month</a></li>
									</ul>
								</div>
								<div class="box-content">

									<div class="tab-content">
									  	<div class="tab-pane" id="24h">
											<div id="chart-24h" style="height:250px;width:100%;"></div>
											<ul class="stats">
												<li>
													<span>17.781</span>
													<h5>Pageviews</h5>
												</li>
												<li>
													<span>9.879</span>
													<h5>Unique Users</h5>
												</li>
												<li>
													<span>00:02:58</span>
													<h5>Avg. Visit Duration</h5>
												</li>
												<li>
													<span>59,83%</span>
													<h5>Bounce Rate</h5>
												</li>
											</ul>
									  	</div>
									  	<div class="tab-pane" id="week">
											<div id="chart-week" style="height:250px;width:100%;"></div>
											<ul class="stats">
												<li>
													<div class="bgchart" id="statsbg1" style="height:93px;width:100%;"></div>
													<span>17.781</span>
													<h5>Pageviews</h5>
												</li>
												<li>
													<div class="bgchart" id="statsbg2" style="height:93px;width:100%;"></div>
													<span>9.879</span>
													<h5>Unique Users</h5>
												</li>
												<li>
													<div class="bgchart" id="statsbg3" style="height:93px;width:100%;"></div>
													<span>00:02:58</span>
													<h5>Avg. Visit Duration</h5>
												</li>
												<li>
													<div class="bgchart" id="statsbg4" style="height:93px;width:100%;"></div>
													<span>59,83%</span>
													<h5>Bounce Rate</h5>
												</li>
											</ul>
									  	</div>
									  	<div class="tab-pane active" id="month">
									  		<div id="chart-month" style="height:250px;width:100%;"></div>
											<ul class="stats">
												<li>
													<span>17.781</span>
													<h5>Pageviews</h5>
												</li>
												<li>
													<span>9.879</span>
													<h5>Unique Users</h5>
												</li>
												<li>
													<span>00:02:58</span>
													<h5>Avg. Visit Duration</h5>
												</li>
												<li>
													<span>59,83%</span>
													<h5>Bounce Rate</h5>
												</li>
											</ul>
									  	</div>
									</div>
										
								</div>
									
							</div>
									
						</div><!--/col-->
						
					</div><!--/row-->
					
					<div class="row">

						<div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
							<div class="box info-box">
								<div class="backgroundColor blue">
								<div class="clearfix">
									<span class="title">SALE</span>
									<span class="value">$1.890,65</span>
								</div>
								<div class="clearfix">
									<span class="date">Today 6:43 AM</span>
									<span class="change">+$432,50 (15,78%)</span>
								</div>	
								<div class="chart-info-box" style="height:50px;margin-top: 20px;"></div>
								</div>
								
								<div class="quarters">
									
									<div class="quarter q1">
										<div>+$780,98 <span>Weekly Change</span></div>
									</div>
									<div class="quarter q2">
										<div class="verticalChart">

											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>37%</span>
													</div>
												</div>
												<div class="title">M</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>16%</span>
													</div>
												</div>
												<div class="title">T</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>12%</span>
													</div>
												</div>
												<div class="title">W</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>9%</span>
													</div>
												</div>
												<div class="title">T</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>7%</span>
													</div>
												</div>
												<div class="title">F</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>6%</span>
													</div>
												</div>
												<div class="title">S</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>5%</span>
													</div>
												</div>
												<div class="title">S</div>
											</div>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="quarter q3">
										9.127
										<span>Deals</span>
									</div>
									<div class="quarter q4">
										$189.783,87
										<span>Total Income</span>
									</div>
									
								</div>	
								
							</div>
						</div><!--/col-->

						<div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
							<div class="box info-box">
								<div class="backgroundColor orange">
								<div class="clearfix">
									<span class="title">SALE</span>
									<span class="value">$1.890,65</span>
								</div>
								<div class="clearfix">
									<span class="date">Today 6:43 AM</span>
									<span class="change">+$432,50 (15,78%)</span>
								</div>	
								<div class="chart-info-box2" style="height:50px;margin-top: 20px;"></div>
								</div>
								
								<div class="quarters">
									
									<div class="quarter q1">
										<div>+$780,98 <span>Weekly Change</span></div>
									</div>
									<div class="quarter q2">
										<div class="verticalChart">

											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>37%</span>
													</div>
												</div>
												<div class="title">M</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>16%</span>
													</div>
												</div>
												<div class="title">T</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>12%</span>
													</div>
												</div>
												<div class="title">W</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>9%</span>
													</div>
												</div>
												<div class="title">T</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>7%</span>
													</div>
												</div>
												<div class="title">F</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>6%</span>
													</div>
												</div>
												<div class="title">S</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>5%</span>
													</div>
												</div>
												<div class="title">S</div>
											</div>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="quarter q3">
										9.127
										<span>Deals</span>
									</div>
									<div class="quarter q4">
										$189.783,87
										<span>Total Income</span>
									</div>
									
								</div>	
								
							</div>
						</div><!--/col-->

						<div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
							<div class="box info-box">
								<div class="backgroundColor red">
								<div class="clearfix">
									<span class="title">SALE</span>
									<span class="value">$1.890,65</span>
								</div>
								<div class="clearfix">
									<span class="date">Today 6:43 AM</span>
									<span class="change">+$432,50 (15,78%)</span>
								</div>	
								<div class="chart-info-box2" style="height:50px;margin-top: 20px;"></div>
								</div>
								
								<div class="quarters">
									
									<div class="quarter q1">
										<div>+$780,98 <span>Weekly Change</span></div>
									</div>
									<div class="quarter q2">
										<div class="verticalChart">

											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>37%</span>
													</div>
												</div>
												<div class="title">M</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>16%</span>
													</div>
												</div>
												<div class="title">T</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>12%</span>
													</div>
												</div>
												<div class="title">W</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>9%</span>
													</div>
												</div>
												<div class="title">T</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>7%</span>
													</div>
												</div>
												<div class="title">F</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>6%</span>
													</div>
												</div>
												<div class="title">S</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>5%</span>
													</div>
												</div>
												<div class="title">S</div>
											</div>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="quarter q3">
										9.127
										<span>Deals</span>
									</div>
									<div class="quarter q4">
										$189.783,87
										<span>Total Income</span>
									</div>
									
								</div>	
								
							</div>
						</div><!--/col-->

						<div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
							<div class="box info-box">
								<div class="backgroundColor pink">
								<div class="clearfix">
									<span class="title">SALE</span>
									<span class="value">$1.890,65</span>
								</div>
								<div class="clearfix">
									<span class="date">Today 6:43 AM</span>
									<span class="change">+$432,50 (15,78%)</span>
								</div>	
								<div class="chart-info-box" style="height:50px;margin-top: 20px;"></div>
								</div>
								
								<div class="quarters">
									
									<div class="quarter q1">
										<div>+$780,98 <span>Weekly Change</span></div>
									</div>
									<div class="quarter q2">
										<div class="verticalChart">

											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>37%</span>
													</div>
												</div>
												<div class="title">M</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>16%</span>
													</div>
												</div>
												<div class="title">T</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>12%</span>
													</div>
												</div>
												<div class="title">W</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>9%</span>
													</div>
												</div>
												<div class="title">T</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>7%</span>
													</div>
												</div>
												<div class="title">F</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>6%</span>
													</div>
												</div>
												<div class="title">S</div>
											</div>
											<div class="singleBar">
												<div class="bar">
													<div class="value">
														<span>5%</span>
													</div>
												</div>
												<div class="title">S</div>
											</div>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="quarter q3">
										9.127
										<span>Deals</span>
									</div>
									<div class="quarter q4">
										$189.783,87
										<span>Total Income</span>
									</div>
									
								</div>	
								
							</div>
						</div><!--/col-->

					</div><!--/row-->
					
					<div class="row">
						
						<div class="col-lg-6">
							<div class="box calendar">	
								<div class="calendar-small"></div>
								<div class="calendar-details">
									<div class="day">MONDAY</div>
									<div class="date">MAY 22</div>
									<ul class="events">
										<li>MAY 22, 19:30 Meeting</li>
										<li>MAY 22, 19:30 Meeting</li>
									</ul>
									<div class="add-event">
										<input type="text" class="new event" placeholder="click here to add event">
									</div>		
								</div>
								<div class="clearfix"></div>
							</div>
						</div><!--/col-->
						
						<div class="col-lg-6">
							
							<div class="row">
								
								<div class="col-md-12">
									
									<div class="box">
										<div class="box-header">
											<h2><i class="fa fa-check"></i>Tasks in Progress</h2>
											<div class="box-icon">
												<a href="index.html#" class="btn-setting"><i class="fa fa-wrench"></i></a>
												<a href="index.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
												<a href="index.html#" class="btn-close"><i class="fa fa-times"></i></a>
											</div>
										</div>
										<div class="box-content no-padding">
											<table class="table bootstrap-datatable datatable small-font">
												<thead>
													  <tr>
														  <th>Task</th>
														  <th>Assigned to</th>
														  <th>Progress</th>
														  <th class="center">Status</th>
													  </tr>
												  </thead>   
												  <tbody>
													<tr>
														<td>SEO Optimisation</td>
														<td>Charly Brown</td>
														<td><div class="progress slim simpleProgress progressGreen">80</div></td>
														<td class="center green">
															Active
														</td>
													</tr>
													<tr>
														<td>App Development</td>
														<td>John Smith</td>
														<td><div class="progress slim simpleProgress progressGreen">95</div></td>
														<td class="center green">
															Active
														</td>
													</tr>
													<tr>
														<td>Hire Developers</td>
														<td>Megan Holms</td>
														<td><div class="progress slim simpleProgress progressLightOrange">27</div></td>
														<td class="center lightOrange">
															Pending
														</td>
													</tr>
													<tr>
														<td>Growt Hacking</td>
														<td>Alan Wane</td>
														<td><div class="progress slim simpleProgress progressGreen">11</div></td>
														<td class="center green">
															Active
														</td>
													</tr>
													<tr>
														<td>A/B Tests</td>
														<td>Irina Cole</td>
														<td><div class="progress slim simpleProgress progressRed">73</div></td>
														<td class="center red">
															Canceled
														</td>
													</tr>											
												</tbody>
											</table>	 	
										</div>
									</div>
									
								</div><!--/col-->
								
								<div class="col-md-12">
									
									<div class="box blue">
										<div class="box-header">
											<h2><i class="fa fa-bar-chart-o"></i>Weekly Stat</h2>
											<div class="box-icon">
												<a href="index.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
												<a href="index.html#" class="btn-close"><i class="fa fa-times"></i></a>
											</div>
										</div>
										<div class="box-content">
											<div class="chart-type1" style="height:170px"></div>	
										</div>	
									</div><!--/span-->
									
								</div><!--/col-->
									
							</div><!--/row-->	

						</div><!--/col-->
							
					</div><!--/row-->
					
					<div class="row">		
						
						<div class="col-lg-4 col-md-4">

							<div class="box">
								<div class="box-header">
									<h2><i class="fa fa-check"></i>To Do List</h2>
									<div class="box-icon">
										<a href="index.html#" class="btn-setting"><i class="fa fa-wrench"></i></a>
										<a href="index.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
										<a href="index.html#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<div class="box-content">
									<div class="todo">
										<ul class="todo-list">
											<li>
												<span class="todo-actions">
													<a href="index.html#"><i class="fa fa-check"></i></a>
												</span>
												<span class="desc">Windows Phone 8 App</span> 
												<span class="label label-important">today</span>
												<a class="remove" href="index.html#"><i class="fa fa-times"></i></a>					
											</li>
											<li>
												<span class="todo-actions">
													<a href="index.html#"><i class="fa fa-check"></i></a>
												</span>
												<span class="desc">New frontend layout</span>
												<span class="label label-important">today</span>
												<a class="remove" href="index.html#"><i class="fa fa-times"></i></a>	
											</li>
											<li>
												<span class="todo-actions">
													<a href="index.html#"><i class="fa fa-check"></i></a>
												</span>
												<span class="desc">Hire developers</span>
												<span class="label label-warning">tommorow</span>
												<a class="remove" href="index.html#"><i class="fa fa-times"></i></a>	
											</li>
											<li>
												<span class="todo-actions">
													<a href="index.html#"><i class="fa fa-check"></i></a>
												</span>
												<span class="desc">Windows Phone 8 App</span>
												<span class="label label-warning">tommorow</span>
												<a class="remove" href="index.html#"><i class="fa fa-times"></i></a>	
											</li>
											<li>
												<span class="todo-actions">
													<a href="index.html#"><i class="fa fa-check"></i></a>
												</span>
												<span class="desc">New frontend layout</span>
												<span class="label label-success">this week</span>
												<a class="remove" href="index.html#"><i class="fa fa-times"></i></a>	
											</li>
											<li>
												<span class="todo-actions">
													<a href="index.html#"><i class="fa fa-check"></i></a>
												</span>
												<span class="desc">Hire developers</span>
												<span class="label label-success">this week</span>
												<a class="remove" href="index.html#"><i class="fa fa-times"></i></a>	
											</li>
											<li>
												<span class="todo-actions">
													<a href="index.html#"><i class="fa fa-check"></i></a>
												</span>
												<span class="desc">New frontend layout</span>
												<span class="label label-info">this month</span>
												<a class="remove" href="index.html#"><i class="fa fa-times"></i></a>	
											</li>
											<li>
												<span class="todo-actions">
													<a href="index.html#"><i class="fa fa-check"></i></a>
												</span>
												<span class="desc">Hire developers</span>
												<span class="label label-info">this month</span>
												<a class="remove" href="index.html#"><i class="fa fa-times"></i></a>	
											</li>
										</ul>
									</div>	
								</div>
							</div>

						</div><!--/col-->
						
						<div class="col-lg-8 col-md-8">
							<div class="box">
								<div class="box-header">
									<h2><i class="fa fa-list"></i>Recent</h2>
									<div class="box-icon">
										<a href="index.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
										<a href="index.html#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
									<ul class="nav nav-tabs" id="recent">
									  	<li class="active"><a href="index.html#tickets">Tickets</a></li>
									  	<li><a href="index.html#users">Users</a></li>
									  	<li><a href="index.html#comments">Comments</a></li>
									</ul>
								</div>
								<div class="box-content no-padding">
									
									<div class="tab-content">
									  	<div class="tab-pane active" id="tickets">
											<table class="table bootstrap-datatable datatable small-font">
												<thead>
													<tr>
														<th>Status</th>
														<th>Date</th>
														<th>Description</th>
														<th>User</th>
														<th>Number</th>
													</tr>
												</thead>   
												<tbody>
													<tr>
														<td><span class="label label-success">Complete</span></td>
														<td>Jul 25, 2012 11:09</td>
														<td>Server problem</td>
														<td>Ashley Tan</td>
														<td><b>[#199278]</b></td>
													</tr>
													<tr>
														<td><span class="label label-warning">Suspended</span></td>
														<td>Jul 25, 2012 11:09</td>
														<td>Mobile App Problem</td>
														<td>Ann Kovalsky</td>
														<td><b>[#199277]</b></td>
													</tr>
													<tr>
														<td><span class="label label-info">In progress</span></td>
														<td>Jul 25, 2012 11:09</td>
														<td>Paypal Issue</td>
														<td>Chris Dan</td>
														<td><b>[#199276]</b></td>
													</tr>
													<tr>
														<td><span class="label label-important">Rejected</span></td>
														<td>Jul 25, 2012 11:09</td>
														<td>IE7 Problem</td>
														<td>John Grand</td>
														<td><b>[#199275]</b></td>
													</tr>
													<tr>
														<td><span class="label label-success">Complete</span></td>
														<td>Jul 25, 2012 11:09</td>
														<td>Mobile App Problem</td>
														<td>Agnes Young</td>
														<td><b>[#199274]</b></td>
													</tr>
													<tr>
														<td><span class="label label-warning">Suspended</span></td>
														<td>Jul 25, 2012 11:09</td>
														<td>Mobile App Problem</td>
														<td>Patricia Doyle</td>
														<td><b>[#199273]</b></td>
													</tr>
													<tr>
														<td><span class="label label-info">In progress</span></td>
														<td>Jul 25, 2012 11:09</td>
														<td>Mobile App Problem</td>
														<td>Melanie Brown</td>
														<td><b>[#199272]</b></td>
													</tr>
																							
												</tbody>
											</table>
									  	</div>
									  	<div class="tab-pane" id="users">
											<ul class="users-list">
												<li>
													<a href="index.html#">
														<img class="avatar" alt="Lucas" src="assets/img/avatar.jpg">
													</a>
													<div class="name">?ukasz Holeczek 
														<div class="dropdown pull-right">
															<a class="fa fa-cogs" data-toggle="dropdown" href="index.html#"></a>
															<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
																<li><a href="index.html#"><i class="fa fa-check"></i> Accept</a></li>
																<li><a href="index.html#"><i class="fa fa-times"></i>Reject</a></li>
																<li><a href="index.html#"><i class="fa fa-minus-sign-alt"></i>Block</a></li>
																<li><a href="index.html#"><i class="fa fa-trash"></i>Delete</a></li>
															</ul>
														</div>
													</div>
													<span class="place"><i class="fa fa-map-marker"></i>Mikolow, POLAND</span>
													<span class="job"><i class="fa fa-briefcase"></i>Founder, BootstrapMaster.com</span>                                 
												</li>
												<li>
													<a href="index.html#">
														<img class="avatar" alt="Bill" src="assets/img/avatar9.jpg">
													</a>
													<div class="name">Bill Cole
														<div class="dropdown pull-right">
															<a class="fa fa-cogs" data-toggle="dropdown" href="index.html#"></a>
															<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
																<li><a href="index.html#"><i class="fa fa-check"></i> Accept</a></li>
																<li><a href="index.html#"><i class="fa fa-times"></i>Reject</a></li>
																<li><a href="index.html#"><i class="fa fa-minus-sign-alt"></i>Block</a></li>
																<li><a href="index.html#"><i class="fa fa-trash"></i>Delete</a></li>
															</ul>
														</div>
													</div>
													<span class="place"><i class="fa fa-map-marker"></i>London, ENGLAND</span>
													<span class="job"><i class="fa fa-briefcase"></i>CEO, Rainbow INC</span>                                 
												</li>
												<li>
													<a href="index.html#">
														<img class="avatar" alt="Jane" src="assets/img/avatar5.jpg">
													</a>
													<div class="name">Jane Sanchez
														<div class="dropdown pull-right">
															<a class="fa fa-cogs" data-toggle="dropdown" href="index.html#"></a>
															<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
																<li><a href="index.html#"><i class="fa fa-check"></i> Accept</a></li>
																<li><a href="index.html#"><i class="fa fa-times"></i>Reject</a></li>
																<li><a href="index.html#"><i class="fa fa-minus-sign-alt"></i>Block</a></li>
																<li><a href="index.html#"><i class="fa fa-trash"></i>Delete</a></li>
															</ul>
														</div>
													</div>
													<span class="place"><i class="fa fa-map-marker"></i>Berlin, GERMANY</span>
													<span class="job"><i class="fa fa-briefcase"></i>Head of Stars Group</span>                               
												</li>
												<li>
													<a href="index.html#">
														<img class="avatar" alt="Kate" src="assets/img/avatar6.jpg">
													</a>
													<div class="name">Kate Presley
														<div class="dropdown pull-right">
															<a class="fa fa-cogs" data-toggle="dropdown" href="index.html#"></a>
															<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
																<li><a href="index.html#"><i class="fa fa-check"></i> Accept</a></li>
																<li><a href="index.html#"><i class="fa fa-times"></i>Reject</a></li>
																<li><a href="index.html#"><i class="fa fa-minus-sign-alt"></i>Block</a></li>
																<li><a href="index.html#"><i class="fa fa-trash"></i>Delete</a></li>
															</ul>
														</div>
													</div>
													<span class="place"><i class="fa fa-map-marker"></i>Madrid, SPAIN</span>
													<span class="job"><i class="fa fa-briefcase"></i>Creative Director, IdeaLab LTD</span>                                  
												</li>
												<li>
													<a href="index.html#">
														<img class="avatar" alt="Lucas" src="assets/img/avatar7.jpg">
													</a>
													<div class="name">Anna Holmes 
														<div class="dropdown pull-right">
															<a class="fa fa-cogs" data-toggle="dropdown" href="index.html#"></a>
															<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
																<li><a href="index.html#"><i class="fa fa-check"></i> Accept</a></li>
																<li><a href="index.html#"><i class="fa fa-times"></i>Reject</a></li>
																<li><a href="index.html#"><i class="fa fa-minus-sign-alt"></i>Block</a></li>
																<li><a href="index.html#"><i class="fa fa-trash"></i>Delete</a></li>
															</ul>
														</div>
													</div>
													<span class="place"><i class="fa fa-map-marker"></i>Paris, FRANCE</span>
													<span class="job"><i class="fa fa-briefcase"></i>Co-Founder, ArtStudio</span>                                 
												</li>
											</ul>
									  	</div>
									  	<div class="tab-pane" id="comments">
									  		<ul class="comments-list">
												<li>
													<a href="index.html#">
														<img class="avatar" alt="Lucas" src="assets/img/avatar.jpg">
													</a>
													<div>
														<strong>?ukasz Holeczek</strong> - Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
													</div>
													<div class="date">4 minutes ago</div>
												</li>
												<li>
													<a href="index.html#">
														<img class="avatar" alt="Bill" src="assets/img/avatar9.jpg">
													</a>
													<div>
														<strong>Bill Cole</strong> - Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
													</div>
													<div class="date">22 hours ago</div>	                                
												</li>
												<li>
													<a href="index.html#">
														<img class="avatar" alt="Jane" src="assets/img/avatar5.jpg">
													</a>
													<div>
														<strong>Jane Sanchez</strong> - Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
													</div>
													<div class="date">2 days ago</div>		                                  
												</li>
												<li>
													<a href="index.html#">
														<img class="avatar" alt="Kate" src="assets/img/avatar6.jpg">
													</a>
													<div>
														<strong>Kate Presley</strong> - Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
													</div>
													<div class="date">10 days ago</div>	                                  
												</li>
											</ul>
										</div>
									</div>
									
								</div>
							</div>	
						</div><!--/col-->
						
					</div><!--/row-->		
	
					
					
				</div><!--/col-->
				
				<div class="col-md-3 visible-md visible-lg" id="feed">
					
					<h2>Activity Feed <a class="fa fa-repeat"></a></h2>
					
					<ul id="filter">
						<li><a class="green" href="index.html#" data-option-value="task">Tasks</a></li>
						<li><a class="red" href="index.html#" data-option-value="comment">Comments</a></li>
						<li><a class="blue" href="index.html#" data-option-value="message">Messages</a></li>
						<li><a href="index.html#" data-option-value="all">All</a></li>
					</ul>
					
					<ul id="timeline">
						
						<li class="task">
							<i class="fa fa-check-square green"></i>
							<div class="title">New website - A/B Tests</div>
							<div class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</div>
							<span class="date">3 seconds ago</span>
							<span class="separator">?</span>
							<span class="name">Megan Abbott</span>
						</li>
						
						<li class="comment">
							<i class="fa fa-comments red"></i>
							<div class="title">Sales increase</div>
							<div class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </div>
							<span class="date">15 minutes ago</span>
							<span class="separator">?</span>
							<span class="name">Ashley Tan</span>
						</li>
						
						<li class="comment">
							<i class="fa fa-comments red"></i>
							<div class="title">New Bootstrap Theme</div>
							<div class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </div>
							<span class="date">Today</span>
							<span class="separator">?</span>
							<span class="name">Ashley Tan</span>
						</li>
						
						<li class="message">
							<i class="fa fa-pencil-square blue"></i>
							<div class="title">Job offer</div>
							<div class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</div>
							<span class="date">Yesterday</span>
							<span class="separator">?</span>
							<span class="name">James Doe</span>
						</li>
						
						<li class="task">
							<i class="fa fa-check-square green"></i>
							<div class="title">Custom Design</div>
							<div class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</div>
							<span class="date">5 days ago</span>
							<span class="separator">?</span>
							<span class="name">Megan Abbott</span>
						</li>		
						
					</ul>
					<a href="index.html#" id="load-more">???</a>		
						
					
				</div><!--/col-->	
				
			</div><!--/row-->	
			
			
			
							
			
     
					
			</div>
			<!-- end: Content -->
				
				</div><!--/row-->		
		
	</div><!--/container-->
	
	
	<div class="modal fade" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Modal title</h4>
				</div>
				<div class="modal-body">
					<p>Here settings can be configured...</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div class="clearfix"></div>
	
	<footer>
		
		<div class="row">
			
			<div class="col-sm-5">
				&copy; 2014 creativeLabs. <a href="http://bootstrapmaster.com">Admin Templates</a> by BootstrapMaster
			</div><!--/.col-->
			
			<div class="col-sm-7 text-right">
				Powered by: <a href="http://bootstrapmaster.com/demo/genius/" alt="Bootstrap Admin Templates">Genius Dashboard</a> | Based on Bootstrap 3.1.1 | Built with brix.io <a href="http://brix.io" alt="Brix.io - Interface Builder">Interface Builder</a>
			</div><!--/.col-->	
			
		</div><!--/.row-->	

	</footer>
		
	<!-- start: JavaScript-->
	<!--[if !IE]>-->

			<script src="assets/js/jquery-2.1.0.min.js"></script>

	<!--<![endif]-->

	<!--[if IE]>
	
		<script src="assets/js/jquery-1.11.0.min.js"></script>
	
	<![endif]-->

	<!--[if !IE]>-->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery-2.1.0.min.js'>"+"<"+"/script>");
		</script>

	<!--<![endif]-->

	<!--[if IE]>
	
		<script type="text/javascript">
	 	window.jQuery || document.write("<script src='assets/js/jquery-1.11.0.min.js'>"+"<"+"/script>");
		</script>
		
	<![endif]-->
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	
		
	
	
	<!-- page scripts -->
	<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="assets/js/jquery.sparkline.min.js"></script>
	<script src="assets/js/fullcalendar.min.js"></script>
	<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="assets/js/excanvas.min.js"></script><![endif]-->
	<script src="assets/js/jquery.flot.min.js"></script>
	<script src="assets/js/jquery.flot.pie.min.js"></script>
	<script src="assets/js/jquery.flot.stack.min.js"></script>
	<script src="assets/js/jquery.flot.resize.min.js"></script>
	<script src="assets/js/jquery.flot.time.min.js"></script>
	<script src="assets/js/jquery.autosize.min.js"></script>
	<script src="assets/js/jquery.placeholder.min.js"></script>
	<script src="assets/js/moment.min.js"></script>
	<script src="assets/js/daterangepicker.min.js"></script>
	<script src="assets/js/jquery.easy-pie-chart.min.js"></script>
	<script src="assets/js/jquery.dataTables.min.js"></script>
	<script src="assets/js/dataTables.bootstrap.min.js"></script>
	
	<!-- theme scripts -->
	<script src="assets/js/custom.min.js"></script>
	<script src="assets/js/core.min.js"></script>
	
	<!-- inline scripts related to this page -->
	<script src="assets/js/pages/index.js"></script>
	
	<!-- end: JavaScript-->
	
</body>
</html>