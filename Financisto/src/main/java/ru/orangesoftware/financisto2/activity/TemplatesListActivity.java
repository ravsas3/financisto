/*******************************************************************************
 * Copyright (c) 2010 Denis Solonenko.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Denis Solonenko - initial API and implementation
 ******************************************************************************/
package ru.orangesoftware.financisto2.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import ru.orangesoftware.financisto2.R;
import ru.orangesoftware.financisto2.adapter.BlotterListAdapter;
import ru.orangesoftware.financisto2.adapter.BlotterListAdapter_;
import ru.orangesoftware.financisto2.blotter.BlotterFilter;
import ru.orangesoftware.financisto2.filter.WhereFilter;


public class TemplatesListActivity extends BlotterActivity {

	public TemplatesListActivity() {}
	
	public TemplatesListActivity(int layoutId) {
		super(layoutId);
	}
	
	@Override
	protected void calculateTotals() {
		// do nothing
	}
	
	@Override
	protected Cursor createCursor() {
		return db.getAllTemplates(blotterFilter);
	}

    @Override
    protected ListAdapter createAdapter(Cursor cursor) {
        BlotterListAdapter adapter = BlotterListAdapter_.getInstance_(this);
        adapter.initWithCursor(cursor);
        adapter.showRunningBalance = false;
        return adapter;
    }

    @Override
	protected void internalOnCreate(Bundle savedInstanceState) {
		super.internalOnCreate(savedInstanceState);
		// remove filter button and totals
		bFilter.setVisibility(View.GONE);
		findViewById(R.id.total).setVisibility(View.GONE);
		internalOnCreateTemplates();
	}

	protected void internalOnCreateTemplates() {
		// change empty list message
		((TextView)findViewById(android.R.id.empty)).setText(R.string.no_templates);
		// fix filter
		blotterFilter = new WhereFilter("templates");
		blotterFilter.eq(BlotterFilter.IS_TEMPLATE, String.valueOf(1));
        blotterFilter.eq(BlotterFilter.PARENT_ID, String.valueOf(0));
	}
	
}
