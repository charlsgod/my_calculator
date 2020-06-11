package com.charlsgod.mycalculator.calculator.views;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.charlsgod.mycalculator.R;
import com.charlsgod.mycalculator.calculator.presenters.CalculatorPresenter;
import com.charlsgod.mycalculator.calculator.presenters.ICalculatorPresenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.charlsgod.mycalculator.commons.interactors.Constants.DIGITS;
import static com.charlsgod.mycalculator.commons.interactors.Constants.FIRST;
import static com.charlsgod.mycalculator.commons.interactors.Constants.SECOND;
import static com.charlsgod.mycalculator.commons.interactors.Constants.THEME;

public class CalculatorView extends AppCompatActivity implements View.OnClickListener,
        ICalculatorView {

    private boolean mIsTimeToClean;
    private String mOperand;

    private TextView mEdtDigits;

    private ICalculatorPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new CalculatorPresenter(this);
        setTheme(getSavedTheme());
        setContentView(R.layout.activity_calculator);
        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
        initAttributes();
        setListeners();
        dataToZero();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the saved variables.
        String savedDigits = savedInstanceState.getString(DIGITS, "0");
        mEdtDigits.setText(savedDigits);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the variables.
        outState.putString(DIGITS, mEdtDigits.getText().toString());
    }

    private void initAttributes() {
        mEdtDigits = findViewById(R.id.edt_digits);
    }

    private void setListeners() {

        mEdtDigits.setOnLongClickListener(v -> {
            mEdtDigits.setCursorVisible(true);
            return false;
        });

        // Operators
        findViewById(R.id.tv_clean).setOnClickListener(this);
        findViewById(R.id.tv_divide).setOnClickListener(this);
        findViewById(R.id.tv_multiply).setOnClickListener(this);
        findViewById(R.id.tv_subtract).setOnClickListener(this);
        findViewById(R.id.tv_add).setOnClickListener(this);
        findViewById(R.id.tv_percentage).setOnClickListener(this);

        // Numbers
        findViewById(R.id.tv_zero).setOnClickListener(this);
        findViewById(R.id.tv_one).setOnClickListener(this);
        findViewById(R.id.tv_two).setOnClickListener(this);
        findViewById(R.id.tv_three).setOnClickListener(this);
        findViewById(R.id.tv_four).setOnClickListener(this);
        findViewById(R.id.tv_five).setOnClickListener(this);
        findViewById(R.id.tv_six).setOnClickListener(this);
        findViewById(R.id.tv_seven).setOnClickListener(this);
        findViewById(R.id.tv_eight).setOnClickListener(this);
        findViewById(R.id.tv_nine).setOnClickListener(this);

        // Functions
        findViewById(R.id.tv_change_symbol).setOnClickListener(this);
        findViewById(R.id.tv_operate).setOnClickListener(this);
        findViewById(R.id.tv_comma).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // Operators
            case R.id.tv_clean:
                mPresenter.clean();
                clean();
                break;
            case R.id.tv_divide:
                divide();
                break;
            case R.id.tv_multiply:
                multiply();
                break;
            case R.id.tv_subtract:
                subtract();
                break;
            case R.id.tv_add:
                add();
                break;
            case R.id.tv_percentage:
                calculatePercentage();
                break;

            // Numbers
            case R.id.tv_zero:
                write(getString(R.string.zero));
                break;
            case R.id.tv_one:
                write(getString(R.string.one));
                break;
            case R.id.tv_two:
                write(getString(R.string.two));
                break;
            case R.id.tv_three:
                write(getString(R.string.three));
                break;
            case R.id.tv_four:
                write(getString(R.string.four));
                break;
            case R.id.tv_five:
                write(getString(R.string.five));
                break;
            case R.id.tv_six:
                write(getString(R.string.six));
                break;
            case R.id.tv_seven:
                write(getString(R.string.seven));
                break;
            case R.id.tv_eight:
                write(getString(R.string.eight));
                break;
            case R.id.tv_nine:
                write(getString(R.string.nine));
                break;

            // Functions
            case R.id.tv_change_symbol:
                changeSymbol();
                break;
            case R.id.tv_operate:
                equal();
                break;
            case R.id.tv_comma:
                addComma();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.first) {
            saveTheme(FIRST);
            return true;
        } else if (id == R.id.second) {
            saveTheme(SECOND);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void clean() {
        mIsTimeToClean = false;
        dataToZero();
        mEdtDigits.setText(getString(R.string.zero));
    }

    private void dataToZero() {
        mOperand = getString(R.string.zero);
    }

    private void write(String string) {
        if (mIsTimeToClean) {
            clean();
        }
        String actualDigits = mEdtDigits.getText().toString();
        if (actualDigits.equals(getString(R.string.zero)) && !string.equals(getString(R.string.comma))) {
            mEdtDigits.setText(string);
        } else {
            String newDigits = actualDigits + string;
            mEdtDigits.setText(newDigits);
        }
    }

    @Override
    public void showResult(String string) {
        mEdtDigits.setText(string);
        mIsTimeToClean = true;
    }

    @Override
    public void changeSymbol() {
        mPresenter.changeSymbol(mEdtDigits.getText().toString());
    }

    @Override
    public void showError() {
        mEdtDigits.setText(R.string.error);
    }

    @Override
    public void add() {
        mOperand = mEdtDigits.getText().toString();
        mPresenter.add(mOperand);
    }

    @Override
    public void subtract() {
        mOperand = mEdtDigits.getText().toString();
        mPresenter.subtract(mOperand);
    }

    @Override
    public void multiply() {
        mOperand = mEdtDigits.getText().toString();
        mPresenter.multiply(mOperand);
    }

    @Override
    public void divide() {
        mOperand = mEdtDigits.getText().toString();
        mPresenter.divide(mOperand);
    }

    @Override
    public void calculatePercentage() {
        mOperand = mEdtDigits.getText().toString();
        mPresenter.calculatePercentage(mOperand);
    }

    @Override
    public void equal() {
        mOperand = mEdtDigits.getText().toString();
        mPresenter.equal(mOperand);
    }

    @Override
    public void addComma() {
        if (!mEdtDigits.getText().toString().contains(getString(R.string.comma))) {
            write(getString(R.string.comma));
        }
    }

    @Override
    public void saveTheme(String value) {
        mPresenter.saveTheme(THEME, value);
        recreate();
    }

    @Override
    public String getTheme(String key) {
        return mPresenter.getTheme(key);
    }

    private int getSavedTheme(){
        String theme = getTheme(THEME);
        switch (theme) {
            case FIRST:
                return R.style.AppTheme_NoActionBar_First;
            case SECOND:
                return R.style.AppTheme_NoActionBar_Second;
            default:
                return R.style.AppTheme_NoActionBar_First;
        }
    }
}
