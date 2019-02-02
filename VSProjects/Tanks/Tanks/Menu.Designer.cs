namespace Tanks
{
    partial class MenuForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MenuForm));
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.ammountTanks = new System.Windows.Forms.NumericUpDown();
            this.ammountApples = new System.Windows.Forms.NumericUpDown();
            this.btn_openForm2 = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.ammountTanks)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.ammountApples)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.BackColor = System.Drawing.Color.Transparent;
            this.label1.Font = new System.Drawing.Font("Calibri", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.label1.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.label1.Location = new System.Drawing.Point(95, 25);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(176, 23);
            this.label1.TabIndex = 0;
            this.label1.Text = "Ammout of enemies:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.BackColor = System.Drawing.Color.Transparent;
            this.label2.Font = new System.Drawing.Font("Calibri", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.label2.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.label2.Location = new System.Drawing.Point(111, 60);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(160, 23);
            this.label2.TabIndex = 1;
            this.label2.Text = "Ammout of apples:";
            // 
            // ammountTanks
            // 
            this.ammountTanks.BackColor = System.Drawing.SystemColors.Info;
            this.ammountTanks.Location = new System.Drawing.Point(303, 30);
            this.ammountTanks.Maximum = new decimal(new int[] {
            8,
            0,
            0,
            0});
            this.ammountTanks.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.ammountTanks.Name = "ammountTanks";
            this.ammountTanks.Size = new System.Drawing.Size(50, 20);
            this.ammountTanks.TabIndex = 2;
            this.ammountTanks.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.ammountTanks.Value = new decimal(new int[] {
            5,
            0,
            0,
            0});
            // 
            // ammountApples
            // 
            this.ammountApples.BackColor = System.Drawing.SystemColors.Info;
            this.ammountApples.Location = new System.Drawing.Point(303, 65);
            this.ammountApples.Maximum = new decimal(new int[] {
            10,
            0,
            0,
            0});
            this.ammountApples.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.ammountApples.Name = "ammountApples";
            this.ammountApples.Size = new System.Drawing.Size(50, 20);
            this.ammountApples.TabIndex = 3;
            this.ammountApples.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.ammountApples.Value = new decimal(new int[] {
            5,
            0,
            0,
            0});
            // 
            // btn_openForm2
            // 
            this.btn_openForm2.BackColor = System.Drawing.Color.Purple;
            this.btn_openForm2.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.btn_openForm2.Font = new System.Drawing.Font("Calibri", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btn_openForm2.ForeColor = System.Drawing.SystemColors.ControlDark;
            this.btn_openForm2.Location = new System.Drawing.Point(248, 269);
            this.btn_openForm2.Name = "btn_openForm2";
            this.btn_openForm2.Size = new System.Drawing.Size(61, 34);
            this.btn_openForm2.TabIndex = 4;
            this.btn_openForm2.Text = "Go";
            this.btn_openForm2.UseVisualStyleBackColor = false;
            this.btn_openForm2.Click += new System.EventHandler(this.btn_openForm2_Click);
            // 
            // MenuForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = global::Tanks.Properties.Resources.fonMenu;
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.ClientSize = new System.Drawing.Size(564, 315);
            this.Controls.Add(this.btn_openForm2);
            this.Controls.Add(this.ammountApples);
            this.Controls.Add(this.ammountTanks);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Cursor = System.Windows.Forms.Cursors.Hand;
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "MenuForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Tanks";
            this.TopMost = true;
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.MenuForm_FormClosing);
            ((System.ComponentModel.ISupportInitialize)(this.ammountTanks)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.ammountApples)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.NumericUpDown ammountTanks;
        private System.Windows.Forms.NumericUpDown ammountApples;
        private System.Windows.Forms.Button btn_openForm2;
    }
}