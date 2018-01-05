<style>
	.login_head {
		text-align: center;
		margin: 20px 0 20px 0;
	}
</style>

<div id="changePasswordForm_div" class="container" style="display:none; width: 400px">
		
	<div class="login_head">
    	<img src="${basePath}/statics/img/admin.png" width=100 class="img-circle">
	</div>
	
	<div class="row">
	    <div class="col-xs-6 col-sm-6 col-md-8 col-md-offset-2">
	    	<form class="form" id="changePasswordForm" method="post" action="../auth/changepwd">
	    		<div class="form-group">
	    			<input type="text" class="form-control" id="username" name="username" value="Admin" readonly>
				</div>
				<div class="form-group">
					<input type="password" class="form-control" autocomplete="off" name="password" value="" placeholder="6-12位，只能由字母、数字、点和下划线组成">
				</div>
	    		<div class="form-group">
					<input type="password" class="form-control" autocomplete="off" name="confirmPassword" placeholder="确认密码">
				</div>
				<input type="hidden" id="from" name="from" value="" >
				<input type="hidden" name="next" value="">
				<input type="hidden" id="_id" name="_id" value="${Session.user.id}" >
				
				<button type="submit" class="btn btn-primary btn-block">修改密码</button>
			</form>
	    </div>
	</div>
</div>