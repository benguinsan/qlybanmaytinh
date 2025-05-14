package DTO;

import java.util.Objects;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ThongKeTonKhoDTO {

    String masp;
    String maphienbansp;
    String tensanpham;
    String chip;
    String card;
    String ram;
    int nhaptrongky;
    int xuattrongky;
    int toncuoiky;

    public ThongKeTonKhoDTO() {
    }

    public ThongKeTonKhoDTO(String masp, String maphienbansp, String tensanpham, String ram, String chip, String card, int nhaptrongky, int xuattrongky, int toncuoiky) {
        this.masp = masp;
        this.maphienbansp = maphienbansp;
        this.tensanpham = tensanpham;
        this.ram = ram;
        this.chip = chip;
        this.card = card;
        this.nhaptrongky = nhaptrongky;
        this.xuattrongky = xuattrongky;
        this.toncuoiky = toncuoiky;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getMaphienbansp() {
        return maphienbansp;
    }

    public void setMaphienbansp(String maphienbansp) {
        this.maphienbansp = maphienbansp;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getNhaptrongky() {
        return nhaptrongky;
    }

    public void setNhaptrongky(int nhaptrongky) {
        this.nhaptrongky = nhaptrongky;
    }

    public int getXuattrongky() {
        return xuattrongky;
    }

    public void setXuattrongky(int xuattrongky) {
        this.xuattrongky = xuattrongky;
    }

    public int getToncuoiky() {
        return toncuoiky;
    }

    public void setToncuoiky(int toncuoiky) {
        this.toncuoiky = toncuoiky;
    }

 
    @Override
    public String toString() {
        return "ThongKeTonKhoDTO{" + "masp=" + masp + ", maphienbansp=" + maphienbansp + ", tensanpham=" + tensanpham + ", ram=" + ram + ", card=" + card + ", chip=" + chip + ", nhaptrongky=" + nhaptrongky + ", xuattrongky=" + xuattrongky + ", toncuoiky=" + toncuoiky + '}';
    }



    
}
