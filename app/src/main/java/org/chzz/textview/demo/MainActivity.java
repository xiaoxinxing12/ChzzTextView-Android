package org.chzz.textview.demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.chzz.textview.CHZZTextView;
import org.chzz.textview.CHZZTextViewType;


public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {

    String text = "是全球最大的中文搜索引擎 最大的中文网站 2000年1月由李彦宏创立于北京中关村 致力于向人们提供 “简单，可依赖”的信息获取方式";
    String[] sentences = new String[]{};
    private int mCounter = 10;
    private TextSwitcher textSwitcher;
    private CHZZTextView mTextView;

    private SeekBar seekBar;
    private RadioGroup radioGroup;
    private Button mSet,mBg;
    private CHZZTextViewType mType;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            updateCounter();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSwitcher = (TextSwitcher) findViewById(R.id.text);
        sentences = text.split(" ");
        mSet = (Button) findViewById(R.id.but_set);
        mBg= (Button) findViewById(R.id.but_bg);
        mSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.removeMessages(0);
                Intent color = new Intent(MainActivity.this, ColorActivity.class);
                color.putExtra("colors", mTextView.getCurrentTextColor());
                startActivityForResult(color, 100);
            }
        });
        mBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.removeMessages(0);
                Intent color = new Intent(MainActivity.this, ColorActivity.class);
                color.putExtra("colors", mTextView.getDrawingCacheBackgroundColor());
                startActivityForResult(color, 101);
            }
        });
        textSwitcher.setFactory(this);

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);

        mTextView = (CHZZTextView) findViewById(R.id.text2);

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setMax(20);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30 + progress);
                mTextView.reset(mTextView.getText());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar.setProgress(10);

        radioGroup = (RadioGroup) findViewById(R.id.typeGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.scale:
                        mTextView.setTextColor(Color.BLACK);
                        mTextView.setBackgroundColor(Color.WHITE);
                        mTextView.setAnimateType(CHZZTextViewType.SCALE);
                        mType = CHZZTextViewType.SCALE;
                        break;
                    case R.id.evaporate:
                        mTextView.setTextColor(Color.BLACK);
                        mTextView.setBackgroundColor(Color.WHITE);
                        mTextView.setAnimateType(CHZZTextViewType.EVAPORATE);
                        mType = CHZZTextViewType.EVAPORATE;
                        break;
                    case R.id.fall:
                        mTextView.setTextColor(Color.BLACK);
                        mTextView.setBackgroundColor(Color.WHITE);
                        mTextView.setAnimateType(CHZZTextViewType.FALL);
                        mType = CHZZTextViewType.FALL;
                        break;
                    case R.id.pixelate:
                        mTextView.setTextColor(Color.BLACK);
                        mTextView.setBackgroundColor(Color.WHITE);
                        mTextView.setAnimateType(CHZZTextViewType.PIXELATE);
                        mType = CHZZTextViewType.PIXELATE;
                        break;
                    case R.id.sparkle:
                        mTextView.setTextColor(Color.WHITE);
                        mTextView.setBackgroundColor(Color.BLACK);
                        mTextView.setAnimateType(CHZZTextViewType.SPARKLE);
                        mType = CHZZTextViewType.SPARKLE;
                        break;
                    case R.id.anvil:
                        mTextView.setTextColor(Color.WHITE);
                        mTextView.setBackgroundColor(Color.BLACK);
                        mTextView.setAnimateType(CHZZTextViewType.ANVIL);
                        mType = CHZZTextViewType.ANVIL;
                        break;
                    case R.id.line:
                        mTextView.setTextColor(Color.WHITE);
                        mTextView.setBackgroundColor(Color.BLACK);
                        mTextView.setAnimateType(CHZZTextViewType.LINE);
                        mType = CHZZTextViewType.LINE;
                        break;
                    case R.id.typer:
                        mTextView.setTextColor(Color.WHITE);
                        mTextView.setBackgroundColor(Color.BLACK);
                        mTextView.setAnimateType(CHZZTextViewType.TYPER);
                        mType = CHZZTextViewType.TYPER;
                        break;
                    case R.id.rainbow:
                        mTextView.setTextColor(Color.WHITE);
                        mTextView.setBackgroundColor(Color.BLACK);
                        mTextView.setAnimateType(CHZZTextViewType.RAINBOW);
                        mType = CHZZTextViewType.RAINBOW;
                        break;
                }

                onClick(radioGroup.findViewById(checkedId));
            }
        });
//
//        mTextView.setTextColor(Color.BLACK);
//        mTextView.setBackgroundColor(Color.WHITE);
//        mTextView.setAnimateType(CHZZTextViewType.SCALE);
//        onClick(findViewById(R.id.scale));
    }

    public void onClick(View v) {
        updateCounter();
    }

    private void updateCounter() {
        mCounter = mCounter >= sentences.length - 1 ? 0 : mCounter + 1;
        textSwitcher.setText(sentences[mCounter]);
        mTextView.animateText(sentences[mCounter]);
    }

    @Override
    public View makeView() {

        TextView t = new TextView(this);
        t.setGravity(Gravity.CENTER);
        t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 60);
        return t;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && null != data) {
            int color = data.getIntExtra("color", -1);
            mTextView.setTextColor(color);
            mTextView.setAnimateType(mType);
            updateCounter();


        }
        if (requestCode == 101 && null != data) {
            int color = data.getIntExtra("color", -1);
            mTextView.setBackgroundColor(color);
            mTextView.setAnimateType(mType);
            updateCounter();
        }
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }
}
