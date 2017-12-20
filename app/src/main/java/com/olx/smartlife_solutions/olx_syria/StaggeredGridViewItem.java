package com.olx.smartlife_solutions.olx_syria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class StaggeredGridViewItem {

	public abstract View getView(LayoutInflater inflater, ViewGroup parent);
	public abstract int getViewHeight(LayoutInflater inflater, ViewGroup parent);
	
}
