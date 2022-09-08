package View;

import Helper.Helper;
import Helper.Config;
import com.patikatourism.Admin;
import com.patikatourism.Hotels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HotelGui extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tabb_hotel;
    private JLabel lbl_welcome;
    private JButton btn_main_logout;
    private JScrollPane scrl_hotel_list;
    private JTable tbl_hotel;
    private JPanel pnl_rooms;
    private JScrollPane scrl_rooms;
    private JTable tbl_rooms;
    private JTextField fld_addhotel_name;
    private JTextField fld_hotel_name;
    private JLabel lbl_hotel_name;
    private JTextField fld_hotel_cty;
    private JLabel lbl_hotel_city;
    private JLabel lbl_stars;
    private JComboBox cmb_hotel_stars;
    private JButton btn_filter;
    private JTextField fld_addhotel_adress;
    private JTextField fld_addhotel_city;
    private JTextField fld_addhotel_stars;
    private JButton btn_addhotel;
    private JTable table1;
    private JLabel lbl_delete_hotel_id;
    private JButton btn_delete_hotels;
    private JTextField fld_delete_hotel_id;
    private DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;
    private DefaultTableModel mdl_room_list;
    private Object[] row_room_list;
    private DefaultTableModel mdl_admin_list;
    private Object[] row_admin_list;

    public HotelGui(){
        add(wrapper);
        setSize(500,500);
        int x = Helper.screenCenterPoint("x",getSize());
        int y = Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Hoşgeldiniz "+ Admin.getName());


        btn_main_logout.addActionListener(e -> {
            dispose();

        });

        //Hotel Section
        mdl_hotel_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object [] col_hotel_list = {"ID","Adı","Adresi","Şehir","Yıldız"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list= new Object[col_hotel_list.length];
        tbl_hotel.setModel(mdl_hotel_list);


            for (Hotels obj : Hotels.getList()) {
                Object[] row = new Object[col_hotel_list.length];
                row_hotel_list[0] = obj.getId();
                row_hotel_list[1] = obj.getName();
                row_hotel_list[2] = obj.getAddress();
                row_hotel_list[3] = obj.getCity();
                row_hotel_list[4] = obj.getStars();
                mdl_hotel_list.addRow(row_hotel_list);

            }



        //Hotel Section
        btn_addhotel.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_addhotel_name)||Helper.isFieldEmpty(fld_addhotel_adress)||Helper.isFieldEmpty(fld_addhotel_city)
                    ||Helper.isFieldEmpty(fld_addhotel_stars)){
                Helper.showMsg("fill");
            }else{
                String name = fld_addhotel_name.getText();
                String address = fld_addhotel_adress.getText();
                String city = fld_addhotel_city.getText();
                String stars = fld_addhotel_stars.getText();
                if(Hotels.add(name,address,city,stars)){
                    Helper.showMsg("Done");

                    loadHotelModel();
                    fld_addhotel_name.setText(null);
                    fld_addhotel_city.setText(null);
                    fld_addhotel_adress.setText(null);
                    fld_addhotel_stars.setText(null);
                }


            }

        });

        btn_filter.addActionListener(e -> {
            String name = fld_hotel_name.getText();
            String city = fld_hotel_cty.getText();
            String stars = cmb_hotel_stars.getSelectedItem().toString();
           // String Query = Hotels.searchQuery(name,city,stars);
            //ArrayList<Hotels> searchingUser = Hotels.searchUserList(Query);
           // loadHotelModel(Hotels.searchUserList(Query));
           // loadHotelModel(searchingUser);

        });
        tbl_hotel.getSelectionModel().addListSelectionListener(e -> {
            try{
                String select_user_id = tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(),0).toString();
                fld_delete_hotel_id.setText(select_user_id);
            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }

        });
        btn_delete_hotels.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_delete_hotel_id)){
                Helper.showMsg("fill");
            }else{
                if(Helper.confirm("sure")){
                    int hotel_id = Integer.parseInt(fld_delete_hotel_id.getText());
                    if(Hotels.delete(hotel_id)) {
                        Helper.showMsg("Done");
                        fld_delete_hotel_id.setText(null);
                        loadHotelModel();
                       // loadEducatorCombo();
                       // loadCourseModel();
                    }else{
                        Helper.showMsg("error");
                    }
                }
            }

        });
    }
    public void loadHotelModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel.getModel();
        clearModel.setRowCount(0);
        for(Hotels obj : Hotels.getList()){
            row_hotel_list[0] = obj.getId();
            row_hotel_list[1] = obj.getName();
            row_hotel_list[2] = obj.getAddress();
            row_hotel_list[3] = obj.getCity();
            row_hotel_list[4] = obj.getStars();
            mdl_hotel_list.addRow(row_hotel_list);

        }

    }
    public static void main(String[] args){
        HotelGui hGui = new HotelGui();

    }


}

