<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
* CoreUI - Free Bootstrap Admin Template
* @version v4.2.2
* @link https://coreui.io/product/free-bootstrap-admin-template/
* Copyright (c) 2023 creativeLabs Łukasz Holeczek
* Licensed under MIT (https://github.com/coreui/coreui-free-bootstrap-admin-template/blob/main/LICENSE)
-->
<!-- Breadcrumb-->
<html lang="en">
<head>
<base href="./">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<meta name="description" content="Energy Monitor">
<meta name="keyword" content="Energy,Monitor,EnergyMonitor,Dashboard">
<title>Energy Monitor</title>
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage"
	content="assets/favicon/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
<!-- Vendors styles-->
<link rel="stylesheet" href="vendors/simplebar/css/simplebar.css">
<link rel="stylesheet" href="css/vendors/simplebar.css">
<!-- Main styles for this application-->
<link href="css/style.css" rel="stylesheet">
<!-- We use those styles to show code examples, you should remove them in your application.-->
<link href="css/examples.css" rel="stylesheet">
<link href="vendors/@coreui/chartjs/css/coreui-chartjs.css"
	rel="stylesheet">

<!-- Link đến Bootstrap CSS từ CDN -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Link đến MDB CSS từ CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.11.0/mdb.min.css"
	crossorigin="anonymous" />

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

</head>
<body>

	<!-- JS -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		type="text/javascript">
		
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
		
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		type="text/javascript"></script>

	<div class="sidebar sidebar-dark sidebar-fixed" id="sidebar">
		<div class="sidebar-brand d-none d-md-flex">
			<svg class="sidebar-brand-full" width="118" height="46"
				alt="CoreUI Logo">
          <use xlink:href="assets/brand/coreui.svg#full"></use>
        </svg>
			<svg class="sidebar-brand-narrow" width="46" height="46"
				alt="CoreUI Logo">
          <use xlink:href="assets/brand/coreui.svg#signet"></use>
        </svg>
		</div>
		<ul class="sidebar-nav" data-coreui="navigation" data-simplebar="">
			<li class="nav-item"><a class="nav-link" href="monitor.jsp">
					<svg class="nav-icon">
              <use
							xlink:href="vendors/@coreui/icons/svg/free.svg#cil-speedometer"></use>
            </svg> Dashboard<span class="badge badge-sm bg-info ms-auto">NEW</span>
			</a></li>

			<li class="nav-divider"></li>
			<li class="nav-title">Extras</li>
			<li class="nav-group"><a class="nav-link nav-group-toggle"
				href="#"> <svg class="nav-icon">
              <use
							xlink:href="vendors/@coreui/icons/svg/free.svg#cil-star"></use>
            </svg> Pages
			</a>
				<ul class="nav-group-items">
					<li class="nav-item"><a class="nav-link" href="login.html"
						target="_top"> <svg class="nav-icon">
                  <use
									xlink:href="vendors/@coreui/icons/svg/free.svg#cil-account-logout"></use>
                </svg> Login
					</a></li>
					<li class="nav-item"><a class="nav-link" href="register.jsp"
						target="_top"> <svg class="nav-icon">
                  <use
									xlink:href="vendors/@coreui/icons/svg/free.svg#cil-account-logout"></use>
                </svg> Register
					</a></li>
					<li class="nav-item"><a class="nav-link" href="404.html"
						target="_top"> <svg class="nav-icon">
                  <use
									xlink:href="vendors/@coreui/icons/svg/free.svg#cil-bug"></use>
                </svg> Error 404
					</a></li>
					<li class="nav-item"><a class="nav-link" href="500.html"
						target="_top"> <svg class="nav-icon">
                  <use
									xlink:href="vendors/@coreui/icons/svg/free.svg#cil-bug"></use>
                </svg> Error 500
					</a></li>
				</ul></li>
	</div>
	<div class="wrapper d-flex flex-column min-vh-100 bg-light">
		<header class="header header-sticky mb-4">
			<div class="container-fluid">
				<button class="header-toggler px-md-0 me-md-3" type="button"
					onclick="coreui.Sidebar.getInstance(document.querySelector('#sidebar')).toggle()">
					<svg class="icon icon-lg">
              <use
							xlink:href="vendors/@coreui/icons/svg/free.svg#cil-menu"></use>
            </svg>
				</button>
				<a class="header-brand d-md-none" href="#"> <svg width="118"
						height="46" alt="CoreUI Logo">
              <use xlink:href="assets/brand/coreui.svg#full"></use>
            </svg></a>
				<ul class="header-nav d-none d-md-flex">
					<li class="nav-item"><a class="nav-link" href="index.html">Energy
							Monitor</a></li>
				</ul>
				<ul class="header-nav ms-auto">
					<li class="nav-item"><a class="nav-link" href="#"> <svg
								class="icon icon-lg">
                  <use
									xlink:href="vendors/@coreui/icons/svg/free.svg#cil-bell"></use>
                </svg></a></li>
					<li class="nav-item"><a class="nav-link" href="#"> <svg
								class="icon icon-lg">
                  <use
									xlink:href="vendors/@coreui/icons/svg/free.svg#cil-list-rich"></use>
                </svg></a></li>
					<li class="nav-item"><a class="nav-link" href="#"> <svg
								class="icon icon-lg">
                  <use
									xlink:href="vendors/@coreui/icons/svg/free.svg#cil-envelope-open"></use>
                </svg></a></li>
				</ul>
				<ul class="header-nav ms-3">
					<li class="nav-item dropdown"><a class="nav-link py-0"
						data-coreui-toggle="dropdown" href="#" role="button"
						aria-haspopup="true" aria-expanded="false">
							<div class="avatar avatar-md">
								<img class="avatar-img" src="assets/img/avatars/avatar.png"
									alt="user@email.com">
							</div>
					</a>
						<div class="dropdown-menu dropdown-menu-end pt-0">
							<div class="dropdown-header bg-light py-2">
								<div class="fw-semibold">Account</div>
							</div>
							<a class="dropdown-item" href="#"> <svg class="icon me-2">
                    <use
										xlink:href="vendors/@coreui/icons/svg/free.svg#cil-bell"></use>
                  </svg> Updates<span class="badge badge-sm bg-info ms-2">42</span></a><a
								class="dropdown-item" href="#"> <svg class="icon me-2">
                    <use
										xlink:href="vendors/@coreui/icons/svg/free.svg#cil-envelope-open"></use>
                  </svg> Messages<span class="badge badge-sm bg-success ms-2">42</span></a><a
								class="dropdown-item" href="#"> <svg class="icon me-2">
                    <use
										xlink:href="vendors/@coreui/icons/svg/free.svg#cil-task"></use>
                  </svg> Tasks<span class="badge badge-sm bg-danger ms-2">42</span></a><a
								class="dropdown-item" href="#"> <svg class="icon me-2">
                    <use
										xlink:href="vendors/@coreui/icons/svg/free.svg#cil-comment-square"></use>
                  </svg> Comments<span class="badge badge-sm bg-warning ms-2">42</span></a>
							<div class="dropdown-header bg-light py-2">
								<div class="fw-semibold">Settings</div>
							</div>
							<a class="dropdown-item" href="#"> <svg class="icon me-2">
                    <use
										xlink:href="vendors/@coreui/icons/svg/free.svg#cil-user"></use>
                  </svg> Profile
							</a><a class="dropdown-item" href="#"> <svg class="icon me-2">
                    <use
										xlink:href="vendors/@coreui/icons/svg/free.svg#cil-settings"></use>
                  </svg> Settings
							</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/logout"> <svg
									class="icon me-2">
                    <use
										xlink:href="vendors/@coreui/icons/svg/free.svg#cil-account-logout"></use>
                  </svg> Logout
							</a>
						</div></li>
				</ul>
			</div>
			<div class="header-divider"></div>
			<div class="container-fluid">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb my-0 ms-2">
						<li class="breadcrumb-item">
							<!-- if breadcrumb is single--> <span>Home</span>
						</li>
						<li class="breadcrumb-item active"><span>Dashboard</span></li>
					</ol>
				</nav>
			</div>
		</header>
		<div class="body flex-grow-1 px-3">
			<div class="container-lg">
				<div class="row">
					<div class="col-sm-6 col-lg-3">
						<div class="card mb-4 text-white bg-primary">
							<div
								class="card-body pb-0 d-flex justify-content-between align-items-start">
								<div>
									<div class="fs-4 fw-semibold" id="voltageValue">
										<span class="fs-6 fw-normal">(<span
											id="percentageValue"></span>% <svg class="icon">
            <use
													xlink:href="vendors/@coreui/icons/svg/free.svg#cil-arrow-bottom"></use>
        </svg>)
										</span>
									</div>
									<div>VOLTAGE</div>
								</div>
								<div class="dropdown">
									<button class="btn btn-transparent text-white p-0"
										type="button" data-coreui-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false">
										<svg class="icon">
                        <use
												xlink:href="vendors/@coreui/icons/svg/free.svg#cil-options"></use>
                      </svg>
									</button>
									<div class="dropdown-menu dropdown-menu-end">
										<a class="dropdown-item" href="#">Action</a><a
											class="dropdown-item" href="#">Another action</a><a
											class="dropdown-item" href="#">Something else here</a>
									</div>
								</div>
							</div>
							<div class="c-chart-wrapper mt-3 mx-3" style="height: 70px;">
								<canvas class="chart" id="card-chart1" height="70"></canvas>
							</div>
						</div>
					</div>
					<!-- /.col-->
					<div class="col-sm-6 col-lg-3">
						<div class="card mb-4 text-white bg-info">
							<div
								class="card-body pb-0 d-flex justify-content-between align-items-start">
								<div>
									<div class="fs-4 fw-semibold" id="currentValue">
										<span class="fs-6 fw-normal">(<span
											id="percentageValue"></span>% <svg class="icon">
            <use
													xlink:href="vendors/@coreui/icons/svg/free.svg#cil-arrow-bottom"></use>
        </svg>)
										</span>
									</div>
									<div>CURRENT</div>
								</div>
								<div class="dropdown">
									<button class="btn btn-transparent text-white p-0"
										type="button" data-coreui-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false">
										<svg class="icon">
                        <use
												xlink:href="vendors/@coreui/icons/svg/free.svg#cil-options"></use>
                      </svg>
									</button>
									<div class="dropdown-menu dropdown-menu-end">
										<a class="dropdown-item" href="#">Action</a><a
											class="dropdown-item" href="#">Another action</a><a
											class="dropdown-item" href="#">Something else here</a>
									</div>
								</div>
							</div>
							<div class="c-chart-wrapper mt-3 mx-3" style="height: 70px;">
								<canvas class="chart" id="card-chart2" height="70"></canvas>
							</div>
						</div>
					</div>
					<!-- /.col-->
					<div class="col-sm-6 col-lg-3">
						<div class="card mb-4 text-white bg-warning">
							<div
								class="card-body pb-0 d-flex justify-content-between align-items-start">
								<div>
									<div class="fs-4 fw-semibold" id="humidityValue">
										<span class="fs-6 fw-normal">(<span
											id="percentageValue"></span>% <svg class="icon">
            <use
													xlink:href="vendors/@coreui/icons/svg/free.svg#cil-arrow-bottom"></use>
        </svg>)
										</span>
									</div>
									<div>HUMIDITY</div>
								</div>
								<div class="dropdown">
									<button class="btn btn-transparent text-white p-0"
										type="button" data-coreui-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false">
										<svg class="icon">
                        <use
												xlink:href="vendors/@coreui/icons/svg/free.svg#cil-options"></use>
                      </svg>
									</button>
									<div class="dropdown-menu dropdown-menu-end">
										<a class="dropdown-item" href="#">Action</a><a
											class="dropdown-item" href="#">Another action</a><a
											class="dropdown-item" href="#">Something else here</a>
									</div>
								</div>
							</div>
							<div class="c-chart-wrapper mt-3" style="height: 70px;">
								<canvas class="chart" id="card-chart3" height="70"></canvas>
							</div>
						</div>
					</div>
					<!-- /.col-->
					<div class="col-sm-6 col-lg-3">
						<div class="card mb-4 text-white bg-danger">
							<div
								class="card-body pb-0 d-flex justify-content-between align-items-start">
								<div>
									<div class="fs-4 fw-semibold" id="pFValue">
										<span class="fs-6 fw-normal">(<span
											id="percentageValue"></span>% <svg class="icon">
            <use
													xlink:href="vendors/@coreui/icons/svg/free.svg#cil-arrow-bottom"></use>
        </svg>)
										</span>
									</div>
									<div>TEMPERATURE</div>
								</div>
								<div class="dropdown">
									<button class="btn btn-transparent text-white p-0"
										type="button" data-coreui-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false">
										<svg class="icon">
                        <use
												xlink:href="vendors/@coreui/icons/svg/free.svg#cil-options"></use>
                      </svg>
									</button>
									<div class="dropdown-menu dropdown-menu-end">
										<a class="dropdown-item" href="#">Action</a><a
											class="dropdown-item" href="#">Another action</a><a
											class="dropdown-item" href="#">Something else here</a>
									</div>
								</div>
							</div>
							<div class="c-chart-wrapper mt-3 mx-3" style="height: 70px;">
								<canvas class="chart" id="card-chart4" height="70"></canvas>
							</div>
						</div>
					</div>
					<!-- /.col-->
				</div>

				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button"
						id="dropdownMenuButton" data-mdb-toggle="dropdown"
						aria-expanded="false">List</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<li><a class="dropdown-item" href="#">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="1"
										id="Checkme1" onclick="handleCheckboxClick('Checkme1')">
									<label class="form-check-label" for="Checkme1">Hiệu điện thế</label>
								</div>
						</a></li>
						<li><a class="dropdown-item" href="#">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="2"
										id="Checkme2" onclick="handleCheckboxClick('Checkme2')">
									<label class="form-check-label" for="Checkme2">Cường độ dòng điện</label>
								</div>
						</a></li>
						<li><a class="dropdown-item" href="#">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="3"
										id="Checkme3" onclick="handleCheckboxClick('Checkme3')">
									<label class="form-check-label" for="Checkme3">Công Suất</label>
								</div>
						</a></li>
					</ul>
				</div>




				<div class="card mb-4">
					<div class="card-body">
						<div class="d-flex justify-content-between">
							<div>
								<h4 class="card-title mb-0">VOLTAGE</h4>
								<div class="small text-medium-emphasis" id="currentDateTime"></div>
							</div>
							<div class="btn-toolbar d-none d-md-block" role="toolbar"
								aria-label="Toolbar with buttons">
								<div class="btn-group btn-group-toggle mx-3"
									data-coreui-toggle="buttons">
									<input class="btn-check" id="option1" type="radio"
										name="options" autocomplete="off" checked> <label
										class="btn btn-outline-secondary active"> Day</label> <input
										class="btn-check" id="option2" type="radio" name="options"
										autocomplete="off"> <label
										class="btn btn-outline-secondary active"> Month</label> <input
										class="btn-check" id="option3" type="radio" name="options"
										autocomplete="off"> <label
										class="btn btn-outline-secondary active"> Year</label>
								</div>
								<button class="btn btn-primary" type="button">
									<svg class="icon">
                                <use
											xlink:href="vendors/@coreui/icons/svg/free.svg#cil-cloud-download"></use>
                            </svg>
								</button>
							</div>
						</div>
						<div class="c-chart-wrapper"
							style="height: 300px; margin-top: 40px;">
							<canvas class="chart" id="main-chart" height="300"></canvas>
						</div>
					</div>
				</div>



				<!-- /.row-->



				<div class="card mb-4">
					<div class="card-body">
						<div class="d-flex justify-content-between">
							<div>
								<h4 class="card-title mb-0">CURRENT</h4>
								<div class="small text-medium-emphasis" id="currentDateTime"></div>
							</div>
							<div class="btn-toolbar d-none d-md-block" role="toolbar"
								aria-label="Toolbar with buttons">
								<div class="btn-group btn-group-toggle mx-3"
									data-coreui-toggle="buttons">
									<input class="btn-check" id="option1" type="radio"
										name="options1" autocomplete="off" checked> <label
										class="btn btn-outline-secondary active"> Day</label> <input
										class="btn-check" id="option2" type="radio" name="options1"
										autocomplete="off"> <label
										class="btn btn-outline-secondary active"> Month</label> <input
										class="btn-check" id="option3" type="radio" name="options1"
										autocomplete="off"> <label
										class="btn btn-outline-secondary active"> Year</label>
								</div>
								<button class="btn btn-primary" type="button">
									<svg class="icon">
                      <use
											xlink:href="vendors/@coreui/icons/svg/free.svg#cil-cloud-download"></use>
                    </svg>
								</button>
							</div>
						</div>
						<div class="c-chart-wrapper"
							style="height: 300px; margin-top: 40px;">
							<canvas class="chart" id="main-chart1" height="300"></canvas>
						</div>
					</div>
				</div>


				<div class="card mb-4">
					<div class="card-body">
						<div class="d-flex justify-content-between">
							<div>
								<h4 class="card-title mb-0">TEMPERATURE</h4>
								<div class="small text-medium-emphasis">12/08/2023</div>
							</div>
							<div class="btn-toolbar d-none d-md-block" role="toolbar"
								aria-label="Toolbar with buttons">
								<div class="btn-group btn-group-toggle mx-3"
									data-coreui-toggle="buttons">
									<input class="btn-check" id="option1" type="radio"
										name="options2" autocomplete="off" checked> <label
										class="btn btn-outline-secondary active"> Day</label> <input
										class="btn-check" id="option2" type="radio" name="options2"
										autocomplete="off"> <label
										class="btn btn-outline-secondary active"> Month</label> <input
										class="btn-check" id="option3" type="radio" name="options2"
										autocomplete="off"> <label
										class="btn btn-outline-secondary active"> Year</label>
								</div>
								<button class="btn btn-primary" type="button">
									<svg class="icon">
                      <use
											xlink:href="vendors/@coreui/icons/svg/free.svg#cil-cloud-download"></use>
                    </svg>
								</button>
							</div>
						</div>
						<div class="c-chart-wrapper"
							style="height: 300px; margin-top: 40px;">
							<canvas class="chart" id="main-chart2" height="300"></canvas>
						</div>
					</div>
				</div>


				<div class="card mb-4">
					<div class="card-body">
						<div class="d-flex justify-content-between">
							<div>
								<h4 class="card-title mb-0">HUMIDITY</h4>
								<div class="small text-medium-emphasis">12/08/2023</div>
							</div>
							<div class="btn-toolbar d-none d-md-block" role="toolbar"
								aria-label="Toolbar with buttons">
								<div class="btn-group btn-group-toggle mx-3"
									data-coreui-toggle="buttons">
									<input class="btn-check" id="option1" type="radio"
										name="options3" autocomplete="off" checked> <label
										class="btn btn-outline-secondary active"> Day</label> <input
										class="btn-check" id="option2" type="radio" name="options3"
										autocomplete="off"> <label
										class="btn btn-outline-secondary active"> Month</label> <input
										class="btn-check" id="option3" type="radio" name="options3"
										autocomplete="off"> <label
										class="btn btn-outline-secondary active"> Year</label>
								</div>
								<button class="btn btn-primary" type="button">
									<svg class="icon">
                      <use
											xlink:href="vendors/@coreui/icons/svg/free.svg#cil-cloud-download"></use>
                    </svg>
								</button>
							</div>
						</div>
						<div class="c-chart-wrapper"
							style="height: 300px; margin-top: 40px;">
							<canvas class="chart" id="main-chart3" height="300"></canvas>
						</div>
					</div>
				</div>


				<div class="card mb-4">
					<div class="card-body">
						<div class="d-flex justify-content-between">
							<div>
								<h4 class="card-title mb-0">POWER</h4>
								<div class="small text-medium-emphasis">12/08/2023</div>
							</div>
							<div class="btn-toolbar d-none d-md-block" role="toolbar"
								aria-label="Toolbar with buttons">
								<div class="btn-group btn-group-toggle mx-3"
									data-coreui-toggle="buttons">
									<input class="btn-check" id="option1" type="radio"
										name="options4" autocomplete="off" checked> <label
										class="btn btn-outline-secondary active"> Day</label> <input
										class="btn-check" id="option2" type="radio" name="options4"
										autocomplete="off"> <label
										class="btn btn-outline-secondary active"> Month</label> <input
										class="btn-check" id="option3" type="radio" name="options4"
										autocomplete="off"> <label
										class="btn btn-outline-secondary active"> Year</label>
								</div>
								<button class="btn btn-primary" type="button">
									<svg class="icon">
                      <use
											xlink:href="vendors/@coreui/icons/svg/free.svg#cil-cloud-download"></use>
                    </svg>
								</button>
							</div>
						</div>
						<div class="c-chart-wrapper"
							style="height: 300px; margin-top: 40px;">
							<canvas class="chart" id="main-chart4" height="300"></canvas>
						</div>
					</div>
				</div>






			</div>
		</div>
	</div>
	<!-- CoreUI and necessary plugins-->
	<script src="vendors/@coreui/coreui/js/coreui.bundle.min.js"></script>
	<script src="vendors/simplebar/js/simplebar.min.js"></script>
	<!-- Plugins and scripts required by this view-->
	<script src="vendors/chart.js/js/chart.min.js"></script>
	<script src="vendors/@coreui/chartjs/js/coreui-chartjs.js"></script>
	<script src="vendors/@coreui/utils/js/coreui-utils.js"></script>
	<script src="js/main.js"></script>
	<script src="js/time.js"></script>

	<!-- Link đến Bootstrap JS và Popper.js từ CDN -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Link đến MDB JS từ CDN -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.11.0/mdb.min.js"></script>


	<script>
		function updateContent() {
			$.ajax({
				type : "GET",
				url : "getDataServlet",
				dataType : "json",
				success : function(data) {
					console.log("success connect!");
					console.log(data);
					
					
					
					$.each(data, function(index, i) {
					
						var newValue1 = "";
						var valueElement = document.getElementById("voltageValue");
						newValue1 = i.voltage;
						valueElement.innerHTML = newValue1;
						
						var newValue = "";
						var valueElement = document.getElementById("currentValue");
						newValue = i.current;
						valueElement.innerHTML = newValue;
						
						
						var newValue2 = "";
						var valueElement = document.getElementById("humidityValue");
						newValue2 = i.humidity;
						valueElement.innerHTML = newValue2;
						
						
						var newValue3 = "";
						var valueElement = document.getElementById("pFValue");
						newValue3 = i.temperature;
						valueElement.innerHTML = newValue3;
					
						
					
						});
	

				},
				error : function(error) {
					console.error("Error fetching data: ", error);
				}
			});
		}

		setInterval(updateContent, 2000);
	</script>
</body>
</html>