<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link ref="shortcut icon" href="" type="image/x-icon" >

    <title>Users</title>

</head>

<body>

        <table border="1" style="text-align: center;">
            <thead>
                <tr>
                    <th width="100">id</th>
                    <th width="100">username</th>
                    <th width="100">birthday</th>
                    <th width="100">sex</th>
                    <th width="100">address</th>
                </tr>
            </thead>

            <tbody>
                <#list users as user>
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.birthday}</td>
                        <td>${user.sex}</td>
                        <td>${user.address}</td>
                    </tr>
                </#list>
            </tbody>
        </table>


</body>
</html>