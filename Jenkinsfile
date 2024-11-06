pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the main branch
                checkout scm
            }
        }
        
        stage('Verify Checkout') {
            steps {
                // List the contents of the workspace to verify checkout
                bat 'dir'
            }
        }
        
        stage('Run Python Script') {
            steps {
                // Run the Python script using the full path to the Python executable
                bat 'C:\\Users\\user\\AppData\\Local\\Programs\\Python\\Python311\\python.exe Preval.py'
            }
        }
    }
}
