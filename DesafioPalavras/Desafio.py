# Mapeamento de palavras para números
num_map = {
    "zero": "0", "one": "1", "two": "2", "three": "3",
    "four": "4", "five": "5", "six": "6", "seven": "7",
    "eight": "8", "nine": "9"
}

def converter_para_digitos(linha):
    digits = ""  
    palavra = ""  
    for char in linha:
        if char.isalpha():  
            palavra += char.lower()  
            if palavra in num_map:  
                digits += num_map[palavra]  
                palavra = "" 
        elif char.isdigit():  
            digits += char
        else:
            palavra = ""  

    return digits

soma = 0

with open('C:\\Users\\Usuario\\PARADIGMAS\\DesafioCalibration\\calibration_text.txt') as arquivo:
    for linha in arquivo:
        digitos = converter_para_digitos(linha)
        
        if len(digitos) >= 2:  
            primeiro_digito = digitos[0]
            ultimo_digito = digitos[-1]
            soma += int(primeiro_digito + ultimo_digito)  
        elif len(digitos) == 1: 
            soma += int(digitos[0] * 2)  
            
print(soma)
