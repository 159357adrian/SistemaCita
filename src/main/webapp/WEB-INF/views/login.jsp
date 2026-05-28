<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="es">
<head>
    <title>Clínica Pacífico - Login</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family:'Poppins', sans-serif;
        }

        body{
            background: linear-gradient(135deg,#0f4c81,#1e88e5);
            height:100vh;
            display:flex;
            justify-content:center;
            align-items:center;
        }

        .container{
            width:900px;
            max-width:95%;
            background:white;
            border-radius:20px;
            overflow:hidden;
            display:flex;
            box-shadow:0 10px 30px rgba(0,0,0,0.2);
        }

        .left{
            width:50%;
            background:
                    linear-gradient(rgba(0,0,0,0.3),
                    rgba(0,0,0,0.3)),
                    url('https://images.unsplash.com/photo-1584515933487-779824d29309?auto=format&fit=crop&w=900&q=80');

            background-size:cover;
            background-position:center;
            color:white;
            display:flex;
            flex-direction:column;
            justify-content:center;
            align-items:center;
            padding:40px;
            text-align:center;
        }

        .left h1{
            font-size:34px;
            margin-bottom:15px;
        }

        .left p{
            font-size:15px;
        }

        .right{
            width:50%;
            padding:50px;
        }

        .tabs{
            display:flex;
            margin-bottom:30px;
        }

        .tab{
            flex:1;
            text-align:center;
            padding:12px;
            cursor:pointer;
            font-weight:600;
            border-bottom:3px solid #ddd;
        }

        .active{
            color:#1e88e5;
            border-bottom:3px solid #1e88e5;
        }

        h2{
            margin-bottom:25px;
            color:#333;
        }

        .input-group{
            margin-bottom:20px;
        }

        input{
            width:100%;
            padding:14px;
            border-radius:10px;
            border:1px solid #ddd;
            outline:none;
            transition:0.3s;
        }

        input:focus{
            border-color:#1e88e5;
        }

        button{
            width:100%;
            padding:14px;
            border:none;
            border-radius:10px;
            background:#1e88e5;
            color:white;
            font-size:16px;
            cursor:pointer;
            transition:0.3s;
        }

        button:hover{
            background:#1565c0;
        }

        .error{
            color:red;
            margin-top:10px;
            text-align:center;
        }

        .form{
            display:none;
        }

        .show{
            display:block;
        }

        @media(max-width:768px){

            .container{
                flex-direction:column;
            }

            .left,
            .right{
                width:100%;
            }

            .left{
                height:220px;
            }
        }

    </style>
</head>
<body>

<div class="container">

    <!-- PANEL IZQUIERDO -->
    <div class="left">
        <h1>Clínica Pacífico</h1>
        <p>
            Gestiona tus citas médicas
            de forma rápida y segura.
        </p>
    </div>

    <!-- PANEL DERECHO -->
    <div class="right">

        <div class="tabs">
            <div class="tab active" onclick="showLogin()">
                Iniciar Sesión
            </div>

            <div class="tab" onclick="showRegister()">
                Registrarse
            </div>
        </div>

        <!-- LOGIN -->
        <div id="loginForm" class="form show">

            <h2>Bienvenido</h2>

            <form action="${pageContext.request.contextPath}/login"
                  method="post">

                <div class="input-group">
                    <input type="text"
                           name="dni"
                           placeholder="Ingrese su DNI"
                           maxlength="8"
                           pattern="[0-9]{8}"
                           required>
                </div>

                <div class="input-group">
                    <input type="password"
                           name="password"
                           placeholder="Contraseña"
                           required>
                </div>

                <button type="submit">
                    Ingresar
                </button>

                <p class="error">
                    ${errorLogin}
                </p>

            </form>
        </div>

        <!-- REGISTRO -->
        <!-- REGISTRO -->
        <div id="registerForm" class="form">

            <h2>Crear Cuenta</h2>

            <form action="${pageContext.request.contextPath}/registro"
                  method="post">

                <div class="input-group">
                    <input type="text"
                           name="nombre"
                           placeholder="Nombres"
                           required>
                </div>

                <div class="input-group">
                    <input type="text"
                           name="apellido"
                           placeholder="Apellidos"
                           required>
                </div>

                <div class="input-group">
                    <input type="text"
                           name="dniRegistro"
                           placeholder="DNI"
                           maxlength="8"
                           pattern="[0-9]{8}"
                           required>
                </div>

                <div class="input-group">
                    <input type="email"
                           name="correo"
                           placeholder="Correo electrónico"
                           required>
                </div>

                <div class="input-group">
                    <input type="text"
                           name="telefono"
                           placeholder="Celular"
                           maxlength="9"
                           pattern="[0-9]{9}"
                           required>
                </div>

                <div class="input-group">
                    <input type="date"
                           name="fechaNacimiento"
                           required>
                </div>

                <div class="input-group">
                    <select name="sexo" required
                            style="width:100%;
                           padding:14px;
                           border-radius:10px;
                           border:1px solid #ddd;">

                        <option value="">
                            Seleccione sexo
                        </option>

                        <option value="Masculino">
                            Masculino
                        </option>

                        <option value="Femenino">
                            Femenino
                        </option>
                    </select>
                </div>

                <div class="input-group">
                    <input type="password"
                           name="passwordRegistro"
                           placeholder="Contraseña"
                           required>
                </div>

                <div class="input-group">
                    <input type="password"
                           name="confirmarPassword"
                           placeholder="Confirmar contraseña"
                           required>
                </div>

                <button type="submit">
                    Registrarme
                </button>

                <p class="error">
                    ${errorRegistro}
                </p>

            </form>
        </div>

    </div>
</div>

<script>

    function showLogin(){

        document.getElementById("loginForm")
            .classList.add("show");

        document.getElementById("registerForm")
            .classList.remove("show");
    }

    function showRegister(){

        document.getElementById("registerForm")
            .classList.add("show");

        document.getElementById("loginForm")
            .classList.remove("show");
    }

</script>

</body>
</html>
