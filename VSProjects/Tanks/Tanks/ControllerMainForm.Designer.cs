namespace Tanks
{
    partial class ControllerMainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(ControllerMainForm));
            this.lbl_Score = new System.Windows.Forms.Label();
            this.MainMenuStrip = new System.Windows.Forms.MenuStrip();
            this.gameToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.newGameToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripSeparator();
            this.exitToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.settingsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.soundToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.helpToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.howToPlayToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.Game_statusStrip = new System.Windows.Forms.StatusStrip();
            this.GameStatus_lbl = new System.Windows.Forms.ToolStripStatusLabel();
            this.StartStop_pcbx = new System.Windows.Forms.PictureBox();
            this.ApplePic = new System.Windows.Forms.PictureBox();
            this.MainMenuStrip.SuspendLayout();
            this.Game_statusStrip.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.StartStop_pcbx)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.ApplePic)).BeginInit();
            this.SuspendLayout();
            // 
            // lbl_Score
            // 
            this.lbl_Score.AutoSize = true;
            this.lbl_Score.BackColor = System.Drawing.Color.Transparent;
            this.lbl_Score.Font = new System.Drawing.Font("Calibri", 20.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.lbl_Score.ForeColor = System.Drawing.Color.Green;
            this.lbl_Score.Location = new System.Drawing.Point(500, 400);
            this.lbl_Score.Name = "lbl_Score";
            this.lbl_Score.Size = new System.Drawing.Size(29, 33);
            this.lbl_Score.TabIndex = 1;
            this.lbl_Score.Text = "0";
            this.lbl_Score.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // MainMenuStrip
            // 
            this.MainMenuStrip.BackColor = System.Drawing.Color.Black;
            this.MainMenuStrip.Font = new System.Drawing.Font("Calibri", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.MainMenuStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.gameToolStripMenuItem,
            this.settingsToolStripMenuItem,
            this.helpToolStripMenuItem});
            this.MainMenuStrip.Location = new System.Drawing.Point(0, 0);
            this.MainMenuStrip.Name = "MainMenuStrip";
            this.MainMenuStrip.RenderMode = System.Windows.Forms.ToolStripRenderMode.System;
            this.MainMenuStrip.Size = new System.Drawing.Size(564, 24);
            this.MainMenuStrip.TabIndex = 4;
            this.MainMenuStrip.Text = "mainMenu";
            // 
            // gameToolStripMenuItem
            // 
            this.gameToolStripMenuItem.BackColor = System.Drawing.SystemColors.ControlText;
            this.gameToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.newGameToolStripMenuItem,
            this.toolStripMenuItem1,
            this.exitToolStripMenuItem});
            this.gameToolStripMenuItem.ForeColor = System.Drawing.SystemColors.Control;
            this.gameToolStripMenuItem.Name = "gameToolStripMenuItem";
            this.gameToolStripMenuItem.Size = new System.Drawing.Size(49, 20);
            this.gameToolStripMenuItem.Text = "&Game";
            // 
            // newGameToolStripMenuItem
            // 
            this.newGameToolStripMenuItem.Name = "newGameToolStripMenuItem";
            this.newGameToolStripMenuItem.Size = new System.Drawing.Size(128, 22);
            this.newGameToolStripMenuItem.Text = "&New game";
            this.newGameToolStripMenuItem.Click += new System.EventHandler(this.NewGameToolStripMenuItem_Click);
            // 
            // toolStripMenuItem1
            // 
            this.toolStripMenuItem1.Name = "toolStripMenuItem1";
            this.toolStripMenuItem1.Size = new System.Drawing.Size(125, 6);
            // 
            // exitToolStripMenuItem
            // 
            this.exitToolStripMenuItem.Name = "exitToolStripMenuItem";
            this.exitToolStripMenuItem.Size = new System.Drawing.Size(128, 22);
            this.exitToolStripMenuItem.Text = "&Exit";
            this.exitToolStripMenuItem.Click += new System.EventHandler(this.exitToolStripMenuItem_Click);
            // 
            // settingsToolStripMenuItem
            // 
            this.settingsToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.soundToolStripMenuItem});
            this.settingsToolStripMenuItem.ForeColor = System.Drawing.SystemColors.Control;
            this.settingsToolStripMenuItem.Name = "settingsToolStripMenuItem";
            this.settingsToolStripMenuItem.Size = new System.Drawing.Size(59, 20);
            this.settingsToolStripMenuItem.Text = "&Settings";
            // 
            // soundToolStripMenuItem
            // 
            this.soundToolStripMenuItem.BackColor = System.Drawing.Color.Black;
            this.soundToolStripMenuItem.ForeColor = System.Drawing.Color.Black;
            this.soundToolStripMenuItem.Name = "soundToolStripMenuItem";
            this.soundToolStripMenuItem.Size = new System.Drawing.Size(122, 22);
            this.soundToolStripMenuItem.Text = "&Sound Off";
            this.soundToolStripMenuItem.Click += new System.EventHandler(this.soundToolStripMenuItem_Click);
            // 
            // helpToolStripMenuItem
            // 
            this.helpToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.howToPlayToolStripMenuItem});
            this.helpToolStripMenuItem.ForeColor = System.Drawing.Color.Snow;
            this.helpToolStripMenuItem.Name = "helpToolStripMenuItem";
            this.helpToolStripMenuItem.Size = new System.Drawing.Size(42, 20);
            this.helpToolStripMenuItem.Text = "&Help";
            // 
            // howToPlayToolStripMenuItem
            // 
            this.howToPlayToolStripMenuItem.Name = "howToPlayToolStripMenuItem";
            this.howToPlayToolStripMenuItem.Size = new System.Drawing.Size(134, 22);
            this.howToPlayToolStripMenuItem.Text = "&How to play";
            this.howToPlayToolStripMenuItem.Click += new System.EventHandler(this.howToPlayToolStripMenuItem_Click);
            // 
            // Game_statusStrip
            // 
            this.Game_statusStrip.BackColor = System.Drawing.Color.Black;
            this.Game_statusStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.GameStatus_lbl});
            this.Game_statusStrip.Location = new System.Drawing.Point(0, 439);
            this.Game_statusStrip.Name = "Game_statusStrip";
            this.Game_statusStrip.Size = new System.Drawing.Size(564, 22);
            this.Game_statusStrip.TabIndex = 5;
            this.Game_statusStrip.Text = "statusStrip1";
            // 
            // GameStatus_lbl
            // 
            this.GameStatus_lbl.BackColor = System.Drawing.Color.Black;
            this.GameStatus_lbl.BorderStyle = System.Windows.Forms.Border3DStyle.Bump;
            this.GameStatus_lbl.ForeColor = System.Drawing.Color.White;
            this.GameStatus_lbl.Name = "GameStatus_lbl";
            this.GameStatus_lbl.Size = new System.Drawing.Size(0, 17);
            // 
            // StartStop_pcbx
            // 
            this.StartStop_pcbx.BackgroundImage = global::Tanks.Properties.Resources.switcher;
            this.StartStop_pcbx.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.StartStop_pcbx.Location = new System.Drawing.Point(443, 48);
            this.StartStop_pcbx.Name = "StartStop_pcbx";
            this.StartStop_pcbx.Size = new System.Drawing.Size(84, 67);
            this.StartStop_pcbx.TabIndex = 3;
            this.StartStop_pcbx.TabStop = false;
            this.StartStop_pcbx.Click += new System.EventHandler(this.btnStart_Stop_Click);
            this.StartStop_pcbx.PreviewKeyDown += new System.Windows.Forms.PreviewKeyDownEventHandler(this.StartStop_pcbx_PreviewKeyDown);
            // 
            // ApplePic
            // 
            this.ApplePic.BackColor = System.Drawing.Color.Transparent;
            this.ApplePic.BackgroundImage = global::Tanks.Properties.Resources.apple;
            this.ApplePic.Location = new System.Drawing.Point(530, 400);
            this.ApplePic.Name = "ApplePic";
            this.ApplePic.Size = new System.Drawing.Size(32, 32);
            this.ApplePic.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.ApplePic.TabIndex = 2;
            this.ApplePic.TabStop = false;
            // 
            // ControllerMainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.Info;
            this.ClientSize = new System.Drawing.Size(564, 461);
            this.Controls.Add(this.Game_statusStrip);
            this.Controls.Add(this.StartStop_pcbx);
            this.Controls.Add(this.ApplePic);
            this.Controls.Add(this.lbl_Score);
            this.Controls.Add(this.MainMenuStrip);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MainMenuStrip = this.MainMenuStrip;
            this.MaximizeBox = false;
            this.Name = "ControllerMainForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Tanks";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Controller_MainForm_FormClosing);
            this.Click += new System.EventHandler(this.btnStart_Stop_Click);
            this.MainMenuStrip.ResumeLayout(false);
            this.MainMenuStrip.PerformLayout();
            this.Game_statusStrip.ResumeLayout(false);
            this.Game_statusStrip.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.StartStop_pcbx)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.ApplePic)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lbl_Score;
        private System.Windows.Forms.PictureBox ApplePic;
        private System.Windows.Forms.PictureBox StartStop_pcbx;
        private System.Windows.Forms.MenuStrip MainMenuStrip;
        private System.Windows.Forms.ToolStripMenuItem gameToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem newGameToolStripMenuItem;
        private System.Windows.Forms.ToolStripSeparator toolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem exitToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem settingsToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem soundToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem helpToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem howToPlayToolStripMenuItem;
        private System.Windows.Forms.StatusStrip Game_statusStrip;
        private System.Windows.Forms.ToolStripStatusLabel GameStatus_lbl;
    }
}

