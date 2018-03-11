 public Form1()
        {
            InitializeComponent();
            
           timer1.Interval = 1000;
           timer1.Start();
        }
 
        int counter = 0;
        private void timer1_Tick(object sender, EventArgs e)
        {
            //создает новый файл(в корень записывать нельзя)
            System.IO.StreamWriter writer = new System.IO.StreamWriter(@"C:\test\process" + counter + ".txt", true);
            //записывает процессы и имя пользователя
            foreach (Process pr in Process.GetProcesses())
                writer.WriteLine(pr + "\n");
            writer.WriteLine("User " + Environment.UserName);
            writer.Close(); //завершает запись
            counter++;
        }