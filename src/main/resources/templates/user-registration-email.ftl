<!DOCTYPE html>
<html lang="it">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${subject}</title>
    <style>
        body {
            background: #e6f2ff; /* Sfondo azzurro chiaro */
            height: 100vh;
            display: flex;
            align-content: center;
            justify-content: center;
            font-family: Arial, sans-serif;
            font-size: 14px;
            margin: 0;
        }

        .card {
            width: 100%;
            max-width: 600px;
            box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
            border-top: 3px solid #007bff; /* Blu acceso */
            border-bottom: 3px solid #007bff; /* Blu acceso */
            border-left: none;
            border-right: none;
            border-radius: 10px;
        }

        @media(max-width:768px) {
            .card {
                width: 90%;
            }
        }

        .header {
            background-color: #212529; /* Grigio scuro */
            padding: 20px 0;
            text-align: center;
            border-radius: 10px 10px 0 0;
        }

        .title {
            color: #000;
            font-weight: 600;
            margin-bottom: 2vh;
            padding: 0 8%;
            font-size: 16px;
        }

        .footer {
            background-color: #212529; /* Grigio scuro */
            padding: 20px 0;
            text-align: center;
            border-radius: 0 0 10px 10px;
            color: #f8f9fa; /* Bianco leggero per il testo */
            font-size: 12px;
        }

        a.btn {
            background-color: #28a745; /* Verde brillante */
            border-radius: 25px;
            padding: 12px 25px;
            display: inline-block;
            color: #fff !important;
            text-decoration: none;
            font-size: 16px;
            font-weight: 600;
            letter-spacing: 1px;
        }
    </style>
</head>
<body style="margin: 0; text-align: center">
<center>
    <div class="card">
        <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#ffffff" style="border: 1px solid #dce1e5; padding: 10px; margin: 0;">
            <tr>
                <td width="2%">&nbsp;</td>
                <td height="75" width="96%" align="center">
                    <div class="header">
                        <p style="color:#007bff;font-family: Arial, sans-serif; font-style:normal; font-size:28px; margin: 5px 0;">Benvenuto/a, ${name}</p>
                    </div>
                </td>
                <td width="2%">&nbsp;</td>
            </tr>
            <tr>
                <td width="2%">&nbsp;</td>
                <td width="96%" align="center">
                    <div class="title">
                        <br>
                        <h4>Grazie per esserti registrato a <b>NexBuy</b>, il nostro e-commerce per lo shopping online.</h4>
                        <h4>Abbiamo ricevuto con successo la tua registrazione e siamo entusiasti di averti nella nostra community di acquirenti.</h4>
                        <h4>Da oggi potrai acquistare i tuoi prodotti preferiti in modo semplice, veloce e sicuro.</h4>
                        <br>
                    </div>
                </td>
                <td width="2%">&nbsp;</td>
            </tr>
            <tr>
                <td width="2%">&nbsp;</td>
                <td width="96%" align="center" style="padding-bottom: 40px;"> <p style="font-size:16px; margin: 20px 0;">
                        <b>Per iniziare a fare acquisti, visita subito il sito:</b>
                    </p>
                    <a href="https://www.nexbuy.com/activate" class="btn" target="_blank">Inizia ad acquistare</a>
                </td>
                <td width="2%">&nbsp;</td>
            </tr>
            <tr>
                <td width="2%">&nbsp;</td>
                <td width="96%" align="center">
                    <div class="footer">
                        <p style="margin: 10px 0;">
                            Puoi gestire le tue informazioni e i tuoi ordini in qualsiasi momento visitando la sezione <b>“Il mio account”</b> sul sito <a href="https://www.nexbuy.com" style="color:#007bff;">www.nexbuy.com</a>.
                        </p>
                        <p style="margin: 10px 0;">
                            Per ulteriori informazioni o assistenza, contatta il nostro team di supporto al numero <br/><b>+39 345 9571714</b>.
                        </p>
                        <p style="margin: 10px 0;">
                            Buono shopping! 🛒
                        </p>
                        <p style="margin: 10px 0;">
                            Il Team NexBuy
                        </p>
                        <hr style="border: 1px solid #495057; margin: 20px 0;"> </div>
                </td>
                <td width="2%">&nbsp;</td>
            </tr>
        </table>
    </div>
</center>
</body>
</html>