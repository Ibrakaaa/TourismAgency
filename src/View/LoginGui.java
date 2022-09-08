package View;

import Helper.Helper;
import Helper.Config;

import javax.swing.*;

    public class LoginGui extends JFrame {
        private JPanel wrapper;
        private JPanel pnl_btm;
        private JPanel pnl_main;
        private JPanel pnl_up;
        private JPanel pnl_down;
        private JLabel lbl_main_name;
        private JTextField fld_main_username;
        private JPasswordField fld_main_pass;
        private JButton bttn_login;
        private JLabel fld_main_name;
        private JLabel fld_user_login;
        private JTextField txtfld_user_login;
        private JPasswordField user_loginpass_fld;

        public LoginGui(){
            add(wrapper);
            setSize(400,500);
            int x = Helper.screenCenterPoint("x",getSize());
            int y = Helper.screenCenterPoint("y",getSize());
            setLocation(x,y);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setTitle(Config.PROJECT_TITLE);
            setVisible(true);


        }




    }


