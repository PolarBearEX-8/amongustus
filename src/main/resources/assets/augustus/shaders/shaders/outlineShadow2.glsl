#version 120

uniform sampler2D texture;
uniform vec2 texelSize;
uniform vec2 direction;
uniform float radius;
uniform vec3 startColor;

varying vec2 uv;

void main() {
    // Multi-Directional Blur für gleichmäßige smooth Outline mit Custom Color
    float totalAlpha = 0.0;
    float totalWeight = 0.0;

    // Prüfe ob direction gesetzt ist (für 2-Pass Kompatibilität)
    float dirLength = length(direction);

    if (dirLength > 0.1) {
        // 2-Pass Modus: Sample in 3 Richtungen gleichzeitig für bessere Coverage
        // Hauptrichtung
        for (float i = -radius; i <= radius; i += 1.0) {
            vec2 offset = direction * i * texelSize;
            vec4 smpl = texture2D(texture, uv + offset);
            float dist = abs(i);
            float falloff = 1.0 - dist / (radius + 1.0);
            totalAlpha += smpl.a * falloff;
            totalWeight += falloff;
        }

        // Diagonale 1 (45°)
        vec2 diag1 = normalize(direction + vec2(-direction.y, direction.x)) * 0.707;
        for (float i = -radius * 0.7; i <= radius * 0.7; i += 1.5) {
            vec2 offset = diag1 * i * texelSize;
            vec4 smpl = texture2D(texture, uv + offset);
            float dist = abs(i);
            float falloff = (1.0 - dist / (radius * 0.7 + 1.0)) * 0.5;
            totalAlpha += smpl.a * falloff;
            totalWeight += falloff;
        }

        // Diagonale 2 (-45°)
        vec2 diag2 = normalize(direction - vec2(-direction.y, direction.x)) * 0.707;
        for (float i = -radius * 0.7; i <= radius * 0.7; i += 1.5) {
            vec2 offset = diag2 * i * texelSize;
            vec4 smpl = texture2D(texture, uv + offset);
            float dist = abs(i);
            float falloff = (1.0 - dist / (radius * 0.7 + 1.0)) * 0.5;
            totalAlpha += smpl.a * falloff;
            totalWeight += falloff;
        }
    }

    // Normalisierung für smooth Verlauf
    if (totalWeight > 0.01) {
        totalAlpha /= totalWeight; // Normalisiere durch theoretisches Maximum
    }

    gl_FragColor = vec4(startColor, totalAlpha);
}
