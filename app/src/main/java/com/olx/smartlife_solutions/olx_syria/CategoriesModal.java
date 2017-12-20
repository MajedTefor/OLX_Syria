package com.olx.smartlife_solutions.olx_syria;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoriesModal implements StaticStrings{



    private JSONArray catsArray;

    Context context;
    CategoriesModal(Context context) {
        this.context = context;
        try {
            catsArray = new JSONArray(CATS_JSON);
        }
        catch (Exception e)
        {}
    }

    List<CategoryModalItem> getAllParents(){
        JSONArray parentsArray = new JSONArray();
        try {
            for(int i = 0; i < catsArray.length(); i++)
            {
                JSONObject currentObject = catsArray.getJSONObject(i);
                if(currentObject.isNull(CAT_PARENT_ID))
                {

                    parentsArray.put(currentObject);
                }

                //

            }

        }
        catch (Exception e){}

        return convertJsonArrayToModalItem(parentsArray);
    }

    List<CategoryModalItem> getAllChilds(String guid)
    {
        return convertJsonArrayToModalItem(searchForChilds(guid));
    }


    private JSONArray searchForChilds(String guid)
    {
        JSONArray result = new JSONArray();
        try{
            for(int i = 0; i < catsArray.length(); i++)
            {
                JSONObject currentObject = catsArray.getJSONObject(i);
                if(currentObject.getString(CAT_PARENT_ID).equals(guid))
                {
                    result.put(currentObject);
                }
            }
            Toast.makeText(context,result.length()+" " , Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            AlertDialog.Builder a = new AlertDialog.Builder(context);
            a.setMessage(e.getMessage());
            a.show();
        }

        return result;
    }

    private List<CategoryModalItem> convertJsonArrayToModalItem(JSONArray jsonArray)
    {
        List<CategoryModalItem> modalItems = new ArrayList<>();
        try{
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject parentObject = jsonArray.getJSONObject(i);
                CategoryModalItem parentItem = new CategoryModalItem();
                parentItem.setName(parentObject.getString(CAT_NAME));
                parentItem.setGuid(parentObject.getString(CAT_GUID));
                parentItem.setImagePath(parentObject.getString(CAT_IMG_PATH));
                parentItem.setHasChilds(parentObject.isNull(CAT_HAS_CHILDREN) || parentObject.getBoolean(CAT_HAS_CHILDREN));
                modalItems.add(parentItem);
            }
        }
        catch (Exception e){

        }
        return modalItems;
    }


    public class CategoryModalItem{
        private String imagePath, name, guid;

        public boolean isHasChilds() {
            return hasChilds;
        }

        public void setHasChilds(boolean hasChilds) {
            this.hasChilds = hasChilds;
        }

        private boolean hasChilds;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
