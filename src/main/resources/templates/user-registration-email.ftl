<!DOCTYPE html>
<html lang="">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${subject}</title>
    <style>
        body {
            background: #ddd3;
            height: 100vh;
            display: flex;
            align-content: center;
            justify-content: center;
            font-family: Muli, serif;
            font-size: 14px;
            margin: 0;
        }

        .card {
            width: 100%;
            max-width: 600px;
            box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
            border-top: 3px solid;
            border-bottom: 3px solid;
            border-left: none;
            border-right: none;
        }

        @media(max-width:768px) {
            .card {
                width: 90%;
            }
        }

        .header {
            background-color: #000000;
            padding: 20px 0;
            text-align: center;
            border-radius: 10px;
        }

        .title {
            color: #000;
            font-weight: 600;
            margin-bottom: 2vh;
            padding: 0 8%;
            font-size: initial;
        }

        .footer {
            background-color: #000000;
            padding: 20px 0;
            text-align: center;
            border-radius: 10px;
            color: white;
            font-size: 10px;
        }
    </style>
</head>
<body style="margin: 0; text-align: center">
<center>
<div class="card" style="margin: 0;">
    <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#ffffff" style="border: 1px solid #dce1e5; padding: 10px; margin: 0; ">
        <tr>
            <td width="2%">&nbsp;</td>
            <td height="75" width="96%" align="center">
                <div class="header">
                    <p style="color:#eea617;font-family: Tahoma,serif; font-style:normal; font-size:28px; margin: 5px 0;">Benvenuto/a, ${name}</p>
                </div>
            </td>
            <td width="2%">&nbsp;</td>
        </tr>
        <tr>
            <td width="2%">&nbsp;</td>
            <td width="96%" align="center">
                <div class="title">
                    <br>
                    <br>
                    <h4>Grazie per esserti registrato a Theater, il nostro software per la gestione di eventi teatrali.</h4>
                    <h4>Abbiamo ricevuto con successo la tua registrazione e siamo entusiasti di averti con noi.</h4>
                    <h4>Per ulteriori informazioni o assistenza, non esitare a contattarci.</h4>
                    <h4>Grazie ancora e benvenuto nel mondo di Theater!</h4>
                    <br>
                </div>
            </td>
            <td style="width: 2%">&nbsp;</td>
        </tr>
        <!-- Inserted Username and Button Section Here -->
        <tr>
            <td style="width: 2%">&nbsp;</td>
            <td style="width: 96%; align-content: center">
                    <span style="font-size: 14px; font-family: Arial, serif; color: #000000; text-align: center; margin-top: 20px; margin-bottom: 20px; display: block;">
                        <span style="font-size: 18px;"><b>Username</b>:</span> <span style="font-size: 16px; display: block;">${username}</span>
                    </span>
                <hr style="border: 1px solid #dce1e5; margin: 20px 0;">
            </td>
            <td style="width: 2%">&nbsp;</td>
        </tr>
        <tr>
            <td style="width: 2%">&nbsp;</td>
            <td style="width: 96%; align-content: center">
                    <span style="font-size: x-small; font-family: Arial,serif; color: #000000; ">
                        <p style=" font-size:14px; margin-top: 5px">
                            <br/>
                        </p>
                        <center>
                            <p style="font-size:14px; margin-bottom: 20px">
                                <span style="font-size: 18px;"><b>Per attivare il tuo account Theater, verifica i tuoi dati e conferma il tuo account.</b></span>
                            </p>
                        </center>
                    </span>
            </td>
            <td style="width: 2%">&nbsp;</td>
        </tr>
        <tr>
            <td style="width: 2%">&nbsp;</td>
            <td style="width: 96%; align-content: center; text-align: center;">
                <table style="margin: 0 auto;">
                    <tr>
                        <td style="width: 60%;">
                            <div style="background-color: #eea617; border-radius: 25px; padding: 10px; display: inline-block; width: 200px;">
                                <a customerName="" style="color: #fff; text-decoration: none; font-size: 16px; font-weight: 550; letter-spacing: 1px;" target="_blank" href="https://www.elis.org/" title="Activate my account">Confirm my account</a>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
            <td style="width: 2%">&nbsp;</td>
        </tr>
        <td style="width: 2%">&nbsp;</td>
        <!-- End of Inserted Section -->
        <tr>
            <td width="2%">&nbsp;</td>
            <td width="96%" align="center">
                <div class="footer">
                    <p style="font-size:14px; margin-top: 20px">
                        Puoi modificare le tue informazioni di accesso in qualsiasi momento visitando la sezione &quot;My Account&quot; situata su <a customerName="" href="https://www.elis.org/" style="color: #eea617;">https://www.nomedelsito.com/</a>.
                    </p>
                    <p style="font-size:14px; margin-top: 20px">
                        Per ulteriori informazioni, non esitate a contattare il nostro team di supporto esperto al numero <br/><br/><b>+39 3459571714</b>.<br/><br/>
                    </p>
                    <p style="font-size:14px;">
                        Saluti,
                    </p>
                    <p style="font-size:14px; margin-bottom: 30px">
                        Il Team Theater
                    </p>
                    <hr style="border: 1px solid #dce1e5; margin: 20px 0;">
                </div>
            </td>
            <td width="2%">&nbsp;</td>
        </tr>
    </table>
</div>
</center>
</body>
</html>