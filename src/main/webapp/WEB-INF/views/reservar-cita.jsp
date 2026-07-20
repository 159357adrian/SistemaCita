<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="es">
<head>
    <title>Reservar Cita</title>

    <meta name="viewport"
          content="width=device-width, initial-scale=1">

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap"
          rel="stylesheet">

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family:'Poppins', sans-serif;
        }

        body{
            background:#f5f7fb;
            padding:40px;
        }

        .container{
            max-width:700px;
            margin:auto;
            background:white;
            border-radius:20px;
            padding:40px;
            box-shadow:0 10px 25px rgba(0,0,0,.1);
        }

        h1{
            text-align:center;
            margin-bottom:30px;
            color:#1e88e5;
        }

        .input-group{
            margin-bottom:20px;
        }

        label{
            display:block;
            margin-bottom:8px;
            font-weight:500;
        }

        input,
        select{
            width:100%;
            padding:14px;
            border-radius:10px;
            border:1px solid #ddd;
            outline:none;
        }

        button{
            width:100%;
            border:none;
            background:#1e88e5;
            color:white;
            padding:15px;
            border-radius:10px;
            cursor:pointer;
            font-size:16px;
        }

        button:hover{
            background:#1565c0;
        }

        .success{
            color:green;
            text-align:center;
            margin-bottom:20px;
        }

        .error{
            color:red;
            text-align:center;
            margin-bottom:20px;
        }

        .top-bar{
            display:flex;
            justify-content:space-between;
            margin-bottom:25px;
        }

        .btn-home{
            text-decoration:none;
            color:white;
            background:#43a047;
            padding:10px 20px;
            border-radius:10px;
        }

        .btn-salir{
            text-decoration:none;
            color:white;
            background:#e53935;
            padding:10px 20px;
            border-radius:10px;
        }

    </style>
</head>
<body>

<div class="container">

    <div class="top-bar">

        <a class="btn-home"
           href="${pageContext.request.contextPath}/home">
            Inicio
        </a>

        <a class="btn-salir"
           href="${pageContext.request.contextPath}/logout">
            Salir
        </a>

    </div>

    <h1>Reservar Cita Médica</h1>

    <p class="success">
        ${mensajeCita}
    </p>

    <p class="error">
        ${errorCita}
    </p>

    <form action="${pageContext.request.contextPath}/reservar-cita"
          method="post">

        <div class="input-group">
            <label>Médico</label>

            <select name="medico" required>
                <option value="">
                    Seleccione un médico
                </option>

                <option value="7">
                    Dr. Juan Pérez - Medicina General
                </option>

                <option value="8">
                    Dra. María Torres - Pediatría
                </option>

                <option value="9">
                    Dr. Carlos Mendoza - Cardiología
                </option>

                <option value="10">
                    Dra. Ana Ruiz - Dermatología
                </option>
            </select>
        </div>

        <div class="input-group">
            <label>Fecha</label>

            <input type="date"
                   name="fecha"
                   required>
        </div>

        <div class="input-group">
            <label>Hora</label>

            <select name="hora" required>
                <option value="">
                    Seleccione hora
                </option>

                <option value="08:00:00">
                    08:00 AM
                </option>

                <option value="09:00:00">
                    09:00 AM
                </option>

                <option value="10:00:00">
                    10:00 AM
                </option>

                <option value="11:00:00">
                    11:00 AM
                </option>

                <option value="14:00:00">
                    02:00 PM
                </option>

                <option value="15:00:00">
                    03:00 PM
                </option>
            </select>
        </div>

        <button type="submit">
            Reservar Cita
        </button>

    </form>

</div>

</body>
</html>
