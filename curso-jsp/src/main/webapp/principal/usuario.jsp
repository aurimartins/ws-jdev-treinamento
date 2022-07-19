<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->
	<jsp:include page="theme-loader.jsp"></jsp:include>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navebarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">

						<!-- Page-header start -->
						<jsp:include page="page-header.jsp"></jsp:include>


						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<h4 class="sub-title">Cadastro de Usuário</h4>

														<form class="form-material">
														
															<div class="form-group form-default">
																<input type="text" name="footer-email" class="form-control" required=""> 
																<span class="form-bar"></span> 
																<label class="float-label">ID:</label>
															</div>
															
															<div class="form-group form-default">
																<input type="text" name="footer-email" class="form-control" required="">
																<span class="form-bar"></span> 
																<label class="float-label">Nome:</label>
															</div>
																
															<div class="form-group form-default">
																<input type="text" name="footer-email" class="form-control" required=""> 
																<span class="form-bar"></span> 
																<label class="float-label">E-mail:</label>
															</div>
															
															<div class="form-group form-default">
																<input type="password" name="footer-email" class="form-control" required=""> 
																<span class="form-bar"></span> 
																<label class="float-label">Password:</label>
															</div>
															
															<button class="btn btn-primary waves-effect waves-light">Voltar</button>
            												<button class="btn btn-success waves-effect waves-light">Salvar</button>
															
														</form>
													</div>
												</div>
											</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Required Jquery -->
	<jsp:include page="javascriptfile.jsp"></jsp:include>
</body>

</html>
