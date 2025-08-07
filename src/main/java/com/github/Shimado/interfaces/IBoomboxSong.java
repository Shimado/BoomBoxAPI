package com.github.Shimado.interfaces;

import com.github.Shimado.nbs.NbsSong;

import java.util.List;

public interface IBoomboxSong {

    String getID();
    void setID(String id);

    String getFilename();
    void setFilename(String filename);

    String getMaterialUnPurchased();
    void setMaterialUnPurchased(String materialUnPurchased);

    int getCustomModelDataUnPurchased();
    void setCustomModelDataUnPurchased(int customModelDataUnPurchased);

    String getMaterialPurchased();
    void setMaterialPurchased(String materialPurchased);

    int getCustomModelDataPurchased();
    void setCustomModelDataPurchased(int customModelDataPurchased);

    String getMaterialPlaylist();
    void setMaterialPlaylist(String materialPlaylist);

    int getCustomModelDataPlaylist();
    void setCustomModelDataPlaylist(int customModelDataPlaylist);

    String getName();
    void setName(String name);

    List<String> getDescription();
    void setDescription(List<String> description);

    double getPrice();
    void setPrice(double price);

    void setPermission(String permission);
    String getPermission();

    int getOwn();
    void addOwn();
    void setOwn(int own);

    int getAuditions();
    void addAuditions();
    void setAuditions(int auditions);

    NbsSong getNbsSong();
    void setNbsSong(NbsSong nbsSong);

    String getCustomOggSoundName();
    void setCustomOggSoundName(String customOggSoundName);

    int getCustomOggSoundLength();
    void setCustomOggSoundLength(int customOggSoundLength);

}
